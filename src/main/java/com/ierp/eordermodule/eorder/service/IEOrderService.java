package com.ierp.eordermodule.eorder.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorder.domain.EOrderDTO;
import com.ierp.eordermodule.eorder.domain.EOrderManageDTO;


public interface IEOrderService {
    public EOrder  save(EOrder entity);
    public Optional<EOrder> findById(Long id);
    public Page<EOrder> getAll(Pageable pageable);
    public EOrder findOneById(Long id);
    public boolean existsById(Long id);
    public long count();
    public void deleteById(Long id);
    public void deleteAll(Long[] ids);
    
    //自定义接口
    public Page<EOrderDTO> queryEOrders(Specification<EOrder> spec,Pageable pageable);//查询符合条件的订单
    //public Page<EOrderDTO> getEOrders(String orderStatus,Pageable pageable );                  //查询个状态订单
    
  //流程业务
    //1.启动流程
    public void startWorkflow(String userId,Long leaveId, Map<String, Object> variables);
    //2.查询流程任务
    public Page<EOrderManageDTO> findTodoTasks(String userId, Pageable pageable);
    //3.签收流程任务
    public void claim(String taskId,String userId);
    //4.完成流程任务
    public void complete(String taskId, Map<String, Object> variables);
}
