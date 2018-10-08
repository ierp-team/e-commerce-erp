package com.ierp.activiti.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.ierp.activiti.domain.WorkTaskDTO;


public interface IWorkTaskService {
    /**
     * 流程业务
     */
    //1.启动流程
     public ProcessInstance startWorkflow(String authenticatedUserId,String processDefinitionKey, String businessKey, Map variables);
    //2.查询流程任务
    public List<WorkTaskDTO> findTodoTasks(String userId);
    //3.签收流程任务
    public void claim(String taskId,String userId);
    //4.完成流程任务
    public void complete(String taskId, Map variables);
    //5.结束(终止)流程实例

}
