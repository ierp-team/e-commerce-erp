package com.ierp.activiti.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;

public class EncodingProcessEngineConfigurationConfigurer implements ProcessEngineConfigurationConfigurer{
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration){
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        
        //SpringProcessEngineConfiguration类为spring boot默认使用的流程引擎配置类。设置后重新部署流程即可
    }
}
