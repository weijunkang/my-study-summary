package com.weijunkang.heinonggong515.controller;

import com.weijunkang.heinonggong515.component.upload.MinIoStorageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/8/10 14:53
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private MinIoStorageUpload upload;

    @GetMapping("/index")
    public String test12() {
        return "index";
    }

}
