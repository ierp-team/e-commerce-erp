package com.ierp.common.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ExtjsStaticResourceConfig extends WebMvcConfigurerAdapter{    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:E://MyProject//springWork//resource-file//admin-dashboard//");
        super.addResourceHandlers(registry);

    }
}

