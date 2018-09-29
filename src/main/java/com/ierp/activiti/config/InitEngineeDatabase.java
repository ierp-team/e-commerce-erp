package com.ierp.activiti.config;
import org.activiti.engine.ProcessEngineConfiguration;
/**
 * 初始化流程引擎数据库
 */
public class InitEngineeDatabase {
    public static void main(String[] args) {
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault().buildProcessEngine();
    }
}
