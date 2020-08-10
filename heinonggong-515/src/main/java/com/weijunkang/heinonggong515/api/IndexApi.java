package com.weijunkang.heinonggong515.api;

import com.weijunkang.heinonggong515.component.upload.MinIoStorageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/8/10 14:49
 */
@RestController
public class IndexApi {

    @Autowired
    private MinIoStorageUpload upload;


    @GetMapping("/")
    public String test() {
        return "index";
    }


}
