package com.ierp.eordermodule.eorder.listener;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorder.service.IEOrderService;
import com.ierp.eordermodule.util.EOrderProductStatus;
import com.ierp.eordermodule.util.EOrderStatus;
import com.ierp.goods.domain.Goods;
import com.ierp.goods.service.IGoodsService;


@Component
@Transactional
public class MatchOrderListener implements TaskListener {

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
        
        //计数器
        int count = 0;
        
        String processInstanceId = delegateTask.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        EOrder eOrder = eOrderService.findOneById(new Long(processInstance.getBusinessKey()));   
        
        List <EOrderProduct> eOrderPoductList= eOrder.getOrderProducts();
        for(EOrderProduct eOrderPoduct : eOrderPoductList){
            //Goods good = goodsService.findById(eOrderPoduct.getGood().getGoodsId()).get();
            if(!(goodsService.existsById(eOrderPoduct.getGood().getGoodsId()))){
                eOrderPoduct.setOrderProductStatus(EOrderProductStatus.NOEXIST);
                count++;
            }else{
                eOrderPoduct.setOrderProductStatus(EOrderProductStatus.EXIST);
            }
        }
        if(count>0){
            eOrder.setOrderStatus(EOrderStatus.NOMATCH);
            taskService.setVariable(delegateTask.getId(), "isExist", false);
        }else{
            eOrder.setOrderStatus(EOrderStatus.MATCHED);
            taskService.setVariable(delegateTask.getId(), "isExist", true);
        }
    }

}
