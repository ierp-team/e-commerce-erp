package com.ierp.eordermodule.eorder.listener;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
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
public class GoodEnoughListener implements ExecutionListener{
    private static final long serialVersionUID = 1L;
    @Autowired
    private IEOrderService eOrderService;

    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private IGoodsService goodsService;
      
    @Override
    public void notify(DelegateExecution execution) {
      //订单分配成功与否判断计数器
        int count =0;
        System.out.println("进入监听器：GoodEnoughListener");
        String processInstanceId = execution.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        EOrder eOrder = eOrderService.findOneById(new Long(processInstance.getBusinessKey()));   
        
        List <EOrderProduct> eOrderPoductList= eOrder.getOrderProducts();    // 拿到订单里面的所有订单产品
        for(EOrderProduct eOrderPoduct : eOrderPoductList){                            // 对每个订单产品进行库存修改操作
        
            int totalNumber = goodsService.sumGoodsStock(eOrderPoduct.getGood().getGoodsUuid());
            int orderQuantity = eOrderPoduct.getQuantity();

            if(totalNumber>=orderQuantity){
                eOrderPoduct.setOrderProductStatus(EOrderProductStatus.ENOUGH);
            }else{
                eOrderPoduct.setOrderProductStatus(EOrderProductStatus.NOENOUGH );
                count++;
            }
        }
        if(count == 0){
            eOrder.setOrderStatus(EOrderStatus.ASSIGNED );
            execution.setVariable("isEnough", true);
            //taskService.setVariable(delegateTask.getId(), "isEnough", true);
        }else{
            eOrder.setOrderStatus(EOrderStatus.NOASSIGN);
            execution.setVariable("isEnough", false);
            //taskService.setVariable(delegateTask.getId(), "isEnough", false);
        }    
        System.out.println("监听器：GoodEnoughListener完成监听");
        
    }
}
