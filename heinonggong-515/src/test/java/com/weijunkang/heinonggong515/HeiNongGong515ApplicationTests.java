package com.weijunkang.heinonggong515;

import com.weijunkang.heinonggong515.component.upload.MinIoStorageUpload;
import com.weijunkang.heinonggong515.config.MinIoAutoConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
class HeiNongGong515ApplicationTests {

    @Autowired
    MinIoStorageUpload minIoStorageUpload;

    @Test
    void contextLoads() throws FileNotFoundException {

        InputStream is = this.getClass().getResourceAsStream("/asdf.jpg");
        String s = minIoStorageUpload.uploadFile(is, "a123.jpg", "abc", "jpg");
        System.out.println(s);


    }

}
