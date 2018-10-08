package com.ierp.eorder.listener;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.eorder.domain.EOrder;
import com.ierp.eorder.service.IEOrderService;
import com.ierp.eorder.util.EOrderStatus;


@Component
@Transactional
public class OutboundListener implements TaskListener{
    private static final long serialVersionUID = 1L;

    @Autowired
    private IEOrderService eOrderService;

    @Autowired
    private RuntimeService runtimeService;
    
    @Override
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        EOrder eOrder = eOrderService.findOneById(new Long(processInstance.getBusinessKey())); 
        
        eOrder.setOrderStatus(EOrderStatus.NODELIVER);
    }
}
