package com.ierp.eordermodule.eorder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.activiti.domain.WorkTaskDTO;
import com.ierp.activiti.service.IWorkTaskService;
import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorder.domain.EOrderDTO;
import com.ierp.eordermodule.eorder.repository.IEOrderRepository;


/**
 * @author ghb, Date:Sep 26, 20183:00:24 PM
 *
 */
@Service
@Transactional
public class EOrderService implements IEOrderService {
    
    @Autowired
    IEOrderRepository eOrderRepository;
    
    @Autowired 
    private IWorkTaskService workTaskService;

    @Override
    public EOrder save(EOrder entity) {
        return eOrderRepository.save(entity);
    }

    @Override
    @Transactional
    public Optional<EOrder> findById(Long id) {
        return eOrderRepository.findById(id);
    }
    
    @Override
    @Transactional
    public EOrder findOneById(Long id){
        return eOrderRepository.findById(id).get();
    }

    @Override
    @Transactional
    public boolean existsById(Long id) {
        return eOrderRepository.existsById(id);
    }

    @Override
    @Transactional
    public long count() {
        return eOrderRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        eOrderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        List<Long> idlist = new ArrayList<Long>(Arrays.asList(ids));
        List<EOrder> eOrderList =  (List<EOrder>)eOrderRepository.findAllById(idlist);
        if(eOrderList!=null) {
            eOrderRepository.deleteAll(eOrderList);
        }
    }

    @Override
    @Transactional
    public Page<EOrder> queryEOrders(Specification<EOrder> spec, Pageable pageable) {
        return eOrderRepository.findAll(spec, pageable);
    }

    @Override
    public Page<EOrder> getEOrders(String orderStatus, Pageable pageable) {
        return eOrderRepository.findEOrder(orderStatus, pageable);
    }
    
    
    /*----------------------------------------------流程业务--------------------------------------------*/
    /**
    * 开始请假流程
    *
    * @param userId 用户ID
    * @param pageable 分页条件
    * @return
    */

   public void startWorkflow(String userId ,Long leaveId, Map<String, Object> variables) 
   {
       //1.声明流程实例
       ProcessInstance processInstance = null;
       //2.获取创建好的请假实例
       EOrder eOrder = eOrderRepository.findById(leaveId).get();
       if(eOrder!=null){
           try {
               processInstance = workTaskService.startWorkflow(userId, "eordermanage", eOrder.getId().toString(), variables);
               eOrder.setProcessInstanceId(processInstance.getId());
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
    /**
    * 查询待办任务
    *
    * @param userId 用户ID
    * @param pageable 分页条件
    * @return
    */

   public Page<EOrderDTO> findTodoTasks(String userId, Pageable pageable) 
   {
       List<EOrderDTO> results = null;
       List<WorkTaskDTO> workTaskLists = workTaskService.findTodoTasks(userId);
       // 根据流程的业务ID查询实体并关联
       if(null!=workTaskLists) {
           results = new ArrayList<EOrderDTO>();
           for (WorkTaskDTO workTask : workTaskLists) {
               Long businessKey = new Long(workTask.getBusinessKey());
               if (workTask.getBusinessKey() == null) {
                   continue;
               }
               EOrder eOrder = eOrderRepository.findById(businessKey).get();
               if(eOrder!=null){
                   EOrderDTO eOrderDTO = new EOrderDTO();
                   BeanUtils.copyProperties(eOrder, eOrderDTO);
                   BeanUtils.copyProperties(workTask, eOrderDTO);
                   results.add(eOrderDTO);
               }
           }
       }
       return new PageImpl<EOrderDTO> (results, pageable, null!=results?results.size():0);
   }

   /**
    * 签收流程任务
    *
    * @param taskId 任务ID
    * @param userId 签收人用户ID
    * @return
    */
   public void claim(String taskId, String userId) {
       workTaskService.claim(taskId, userId);
   }
    /**
    * 完成流程任务
    *
    * @param taskId 任务ID
    * @param variables 流程变量
    * @return
    */
   public void complete(String taskId, Map<String, Object> variables) {
       workTaskService.complete(taskId, variables);
   }

 
}
