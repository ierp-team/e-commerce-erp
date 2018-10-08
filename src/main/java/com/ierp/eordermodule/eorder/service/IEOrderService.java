package com.ierp.eordermodule.eorder.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorder.domain.EOrderDTO;


public interface IEOrderService {
    public EOrder  save(EOrder entity);
    public Optional<EOrder> findById(Long id);
    public EOrder findOneById(Long id);
    public boolean existsById(Long id);
    public long count();
    public void deleteById(Long id);
    public void deleteAll(Long[] ids);
    public Page<EOrder> queryEOrders(Specification<EOrder> spec,Pageable pageable);
    public Page<EOrder> getEOrders(String orderStatus,Pageable pageable );
    
  //流程业务
    //1.启动流程
    public void startWorkflow(String userId,Long leaveId, Map<String, Object> variables);
    //2.查询流程任务
    public Page<EOrderDTO> findTodoTasks(String userId, Pageable pageable);
    //3.签收流程任务
    public void claim(String taskId,String userId);
    //4.完成流程任务
    public void complete(String taskId, Map<String, Object> variables);
}
