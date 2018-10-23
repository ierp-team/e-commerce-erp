package com.ierp.eordermodule.eorder.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.common.web.SessionUtil;
import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorder.service.IEOrderService;
import com.ierp.eordermodule.util.EOrderStatus;

@Component
@Transactional
public class AfterModifyListener implements TaskListener{
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private IEOrderService eOrderService;
    @Autowired
    private RuntimeService runtimeService;
    
    public String getUserName(HttpSession session){
        return SessionUtil.getUserName(session);
    }
        
    public void notify(DelegateTask delegateTask) {
        
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        EOrder eOrder = eOrderService.findOneById(new Long(processInstance.getBusinessKey()));   
        
        eOrder.setOrderStatus(EOrderStatus.RECEIVED);
        eOrder.setModifyTime(new Date());
    }
        
}
