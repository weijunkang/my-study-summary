package com.weijunkang.heinonggong515;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/8/10 13:35
 */
@Slf4j
@SpringBootApplication
public class HeiNongGong515Application {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(HeiNongGong515Application.class, args);
        TomcatServletWebServerFactory tomcatServlet = application.getBean(TomcatServletWebServerFactory.class);
        String ip = InetAddress.getLocalHost().getHostAddress();
        int port = tomcatServlet.getPort();
        String path = tomcatServlet.getContextPath();
        log.info("\n----------------------------------------------------------\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }
}
