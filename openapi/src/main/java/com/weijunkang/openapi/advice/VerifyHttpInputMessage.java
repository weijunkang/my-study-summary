package com.weijunkang.openapi.advice;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.weijunkang.openapi.annotation.Verify;
import com.weijunkang.openapi.config.SecurityVerifyConfig;
import com.weijunkang.openapi.exception.VerifyRequestException;
import com.weijunkang.openapi.utils.VerifyUtil;
import com.weijunkang.openapi.vo.RequestParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.*;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 验证Http输入消息
 * @date 2020/8/13 14:07
 */
@Slf4j
public class VerifyHttpInputMessage implements HttpInputMessage {

    private HttpHeaders headers;

    private InputStream body;

    public VerifyHttpInputMessage(HttpInputMessage inputMessage,
                                  SecurityVerifyConfig securityVerifyConfig,
                                  Verify verify,
                                  InitVerifyData initVerifyData) throws IOException {

        this.headers = inputMessage.getHeaders();
        String content = new BufferedReader(new InputStreamReader(inputMessage.getBody()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        if (StrUtil.isEmpty(content)) {
            throw new VerifyRequestException("参数错误");
        }
        JSONObject json = JSONUtil.parseObj(content);
        verify(json, initVerifyData);
        if (securityVerifyConfig.isShowLog()) {
            log.info("收到请求参数：{}", content);
        }
        if(securityVerifyConfig.isTimestampCheck()){
            verifyTimestamp(verify, json);
        }
        this.body = new ByteArrayInputStream(content.getBytes());
    }


    /**
     * 验证请求参数 签名等
     * @param json 请求参数
     */
    private void verify(JSONObject json, InitVerifyData initVerifyData) {
        String sign = verifyParam(Convert.toStr(json.get(VerifyUtil.SIGN)), "签名不存在") ;
        String channelCode = verifyParam(Convert.toStr(json.get(VerifyUtil.CHANNEL_CODE)), "渠道代码不存在");
        String key = verifyParam(initVerifyData.getKey(channelCode), "渠道不存在");

        String apiName = verifyParam(Convert.toStr(json.get(VerifyUtil.API_NAME)), "接口不存在");
        verifyApiName(apiName, initVerifyData);

        String timestamp = verifyParam(Convert.toStr(json.get(VerifyUtil.TIMESTAMP)), "时间戳错误");
        String data = Convert.toStr(json.get(VerifyUtil.DATA));

        RequestParam r = new RequestParam();
        r.setApiName(apiName).setChannelCode(channelCode).setTimestamp(timestamp).setData(data);

        String newSign = SecureUtil.md5(VerifyUtil.paramOrderBy(r) + key);

        //使用ThreadLocal 处理全局（报错需要返回） 需要得到 请求接口名称
//        MyRequestHolder.add(apiName);
        if (!sign.equals(newSign)) {
            log.error("签名错误");
            throw new VerifyRequestException("签名错误");
        }
    }

    /**
     * 验证接口是否存在
     * hz.partner.order(类).getOrderInfo(方法名)
     * @param apiName
     */
    private void verifyApiName(String apiName, InitVerifyData initVerifyData) {
        String[] split = apiName.split("\\.");
        if(split.length != 4 || !StrUtil.startWith(apiName, VerifyUtil.API_NAME_PREFIX)){
            throw new VerifyRequestException("接口不存在");
        }
        String methodName = split[3];
        if (!initVerifyData.existMethod(methodName)) {
            throw new VerifyRequestException("接口不存在");
        }
    }

    /**
     * 验证参数 为null的参数抛出错误
     * @param param 参数
     * @param msg 错误消息
     * @return
     */
    private String verifyParam(String param, String msg) {
        return Optional.ofNullable(param).orElseThrow(() -> new VerifyRequestException(msg));
    }

    /**
     * 时间戳检查
     * @param verify
     * @param json
     */
    private void verifyTimestamp(Verify verify, JSONObject json) {
        // 容忍最小请求时间
        long toleranceTime = System.currentTimeMillis() - verify.timeout();
        long requestTime = Convert.toLong(json.get(VerifyUtil.TIMESTAMP));
        // 如果请求时间小于最小容忍请求时间, 判定为超时
        if (requestTime < toleranceTime) {
            log.error("请求已超时{}, requestTime:{}", toleranceTime, requestTime);
            throw new VerifyRequestException("request timeout");
        }
    }

    @Override
    public InputStream getBody() {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }

}
