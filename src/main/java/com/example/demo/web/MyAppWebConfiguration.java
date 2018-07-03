package com.example.demo.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class MyAppWebConfiguration extends WebMvcConfigurerAdapter {
   @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

         registry.addResourceHandler("/Path/**").addResourceLocations("file:/D:/aaa/demo/src/main/");
        super.addResourceHandlers(registry);
    }

}
