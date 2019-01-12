package com.potato.smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 约定优先配置
 * <p>
 * 在SpringApplication.run()方法执行后，Spring Boot的autoconfigure发现这是一个Web应用（根据类路径上的依赖确定），
 * 于是在内嵌的Tomcat容器中启动了一个Spring的应用上下文，并且监听默认的tcp端口8080（默认约定）。
 * 同时在Spring Context中根据默认的约定配置了Spring WebMvc： *
 * Servlet容器默认的Context路径是/
 * DispatcherServlet匹配的路径(servlet-mapping中的url-patterns)是/*
 * ComponentScan路径被默认设置为SampleController的同名package，也就是该package下的所有@Controller *
 * Service, Component, Repository都会被实例化后并加入Spring Context中。
 * </p>
 */
@SpringBootApplication
public class SmartApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartApplication.class, args);
    }
}