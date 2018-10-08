package com.ierp.eorder.listener;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.eorder.domain.EOrder;
import com.ierp.eorder.domain.EOrderProduct;
import com.ierp.eorder.service.IEOrderService;
import com.ierp.eorder.util.EOrderStatus;
import com.ierp.goods.service.IGoodsService;

@Component
@Transactional
public class AssignGoodListener implements TaskListener{
    private static final long serialVersionUID = 1L;
    @Autowired
    private IEOrderService eOrderService;

    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private IGoodsService goodsService;
    
    public void notify(DelegateTask delegateTask) {
        
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        EOrder eOrder = eOrderService.findOneById(new Long(processInstance.getBusinessKey()));   
        List <EOrderProduct> eOrderPoductList= eOrder.getOrderProducts();    // 拿到订单里面的所有订单产品
        for(EOrderProduct eOrderPoduct : eOrderPoductList){                            // 对每个订单产品进行库存修改操作
      
//            int totalNumber = goodsService.getNumberByGoodCode(eOrderPoduct.getGood().getGoodsCode());
//            int quantity = eOrderPoduct.getQuantity();
//            int finalNumber = totalNumber-quantity;
//    
//            goodsService.setNumber(eOrderPoduct.getGood().getGoodsCode(),finalNumber);
        }

        eOrder.setOrderStatus(EOrderStatus.ASSIGNED);
             
    }
}
