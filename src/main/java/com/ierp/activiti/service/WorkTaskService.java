package com.ierp.activiti.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.activiti.domain.WorkTaskDTO;

@Service
@Transactional
public class WorkTaskService implements IWorkTaskService {

    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;

    
    /**
     * 启动流程任务
     */
    @SuppressWarnings("unchecked")
    public ProcessInstance startWorkflow(String authenticatedUserId, String processDefinitionKey, String businessKey,
            Map variables) {
        ProcessInstance processInstance = null; //1.声明流程实例
        try {
            identityService.setAuthenticatedUserId(authenticatedUserId);//2.授权
          //3.启动流程实例:processDefinitionKey, businessKey, variables
            processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {//不管 try是否抛出异常，即使try里面包含了return ，返回之前也会执行finally操作,有助于我们处理一些比如 关闭数据库连接，不管是否发生异常，都会先关闭连接然后做汇报上级的操作。
            identityService.setAuthenticatedUserId(null);   //4.取消授权
        }
        return processInstance;
    }

    /**
     * 查找流程任务
     */
    public List<WorkTaskDTO> findTodoTasks(String userId) {
        List<WorkTaskDTO> results=null;
        // 根据当前人的ID查询
        TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
        List<Task> tasks = taskQuery.list();
        if(null!=tasks) {
            results= new ArrayList<WorkTaskDTO>();
             // 根据流程的业务ID查询实体并关联
            for (Task task : tasks) {
                String processInstanceId = task.getProcessInstanceId();
                ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
                String businessKey = processInstance.getBusinessKey();
                if (businessKey == null) {
                    continue;
                }
                WorkTaskDTO dto = new WorkTaskDTO();
                dto.setBusinessKey(processInstance.getBusinessKey());
                
                dto.setTaskId(task.getId());
                dto.setTaskName(task.getName());
                dto.setTaskCreateTime(task.getCreateTime());
                dto.setAssignee(task.getAssignee());
                dto.setTaskDefinitionKey(task.getTaskDefinitionKey());
                dto.setSuspended(processInstance.isSuspended());

                ProcessDefinition processDefinition = getProcessDefinition(processInstance.getProcessDefinitionId());
                dto.setProcessDefinitionId(processDefinition.getId());
                dto.setVersion(processDefinition.getVersion());
                
                results.add(dto);
            }
        }
        return results;
    }
    
    /**
     * 签收流程任务
     * @param taskId 任务ID
     * @param userId 签收人用户ID
     */
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }
    
    /**
     * 完成流程任务
     * @param taskId 任务ID
     * @param variables 流程变量
     */
    public void complete(String taskId, Map variables) {
        taskService.complete(taskId, variables);
    }
    
    /**
     * 查询流程定义对象
     * @param processDefinitionId 流程定义ID
     */
    protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }
 }


