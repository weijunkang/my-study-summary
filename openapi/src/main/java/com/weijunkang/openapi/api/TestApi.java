package com.weijunkang.openapi.api;

import com.weijunkang.openapi.annotation.Verify;
import com.weijunkang.openapi.service.ItemNoService;
import com.weijunkang.openapi.service.UpdateVerifyDataService;
import com.weijunkang.openapi.vo.ApiParam;
import com.weijunkang.openapi.vo.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author weijunkang
 * @version 1.0
 * @description: 测试
 * @date 2020/8/13 14:52
 */
@RestController
public class TestApi {

    @Autowired
    private UpdateVerifyDataService updateVerifyDataService;

    @Autowired
    private ItemNoService itemNoService;

    @Verify
    @PostMapping("/testApi")
    public BizResult test(@RequestBody ApiParam param) {
        return BizResult.success("成功", itemNoService.process(param));
    }

    @GetMapping("/test111")
    public String test1() {
        updateVerifyDataService.updateVerifyData();
        return "2222";
    }

}
