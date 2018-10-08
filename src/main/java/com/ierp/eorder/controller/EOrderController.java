package com.ierp.eorder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.eorder.domain.EOrder;
import com.ierp.eorder.domain.EOrderQueryDTO;
import com.ierp.eorder.service.IEOrderService;
import com.ierp.common.beans.BeanUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;


@RestController
@RequestMapping(value="/eorder")
public class EOrderController {

    @Autowired 
    private IEOrderService eOrderService;
    
    @GetMapping
    public Page<EOrder>  getPage(EOrderQueryDTO eOrderQueryDTO , ExtjsPageRequest pageRequest) 
    {
        return eOrderService.queryEOrders(EOrderQueryDTO.getWhereClause(eOrderQueryDTO), pageRequest.getPageable());
    }
    
//    @PostMapping
//    public  ExtAjaxResponse save(HttpSession session,@RequestBody Leave leave) {
//        try {
//            String userId = SessionUtil.getUserName(session);
//            if(userId!=null){
//                leave.setUserId(userId);
//                leave.setProcessStatus(ProcessStatus.NEW);
//                leaveService.save(leave);
//            }
//            return new ExtAjaxResponse(true,"操作成功!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ExtAjaxResponse(false,"操作失败!");
//        }
//    }
//    
    @PutMapping(value="{id}")
    public @ResponseBody ExtAjaxResponse update(@PathVariable("id") Long id,@RequestBody EOrder eOrder) {
        try {
            EOrder entity = eOrderService.findOneById(id);
            if(entity!=null) {
                BeanUtils.copyProperties(eOrder, entity);//使用自定义的BeanUtils
                eOrderService.save(entity);
            }
            return new ExtAjaxResponse(true,"更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ExtAjaxResponse(false,"操作失败!");
        }
    }
  
    @DeleteMapping
    public @ResponseBody ExtAjaxResponse delete(Long id) {
        try {
            EOrder entity = eOrderService.findOneById(id);//用于先判断是否查得到
            if(entity!=null) {
                eOrderService.deleteById(id);
            }
            return new ExtAjaxResponse(true,"删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ExtAjaxResponse(false,"操作失败!");
        }
    }

    @PostMapping("/deletes")
    public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
    {
        try {
            if(ids!=null) {
                eOrderService.deleteAll(ids);
            }
            return new ExtAjaxResponse(true,"批量删除成功！");
        } catch (Exception e) {
            return new ExtAjaxResponse(true,"批量删除失败！");
        }
    }
    
    @GetMapping
    public Page<EOrder> getEOrderList(@RequestParam String orderStatus,ExtjsPageRequest pageable) {
        Page<EOrder> page;//接收结果集
        if(orderStatus!=null) {
            page = eOrderService.getEOrders(orderStatus,pageable.getPageable());
        }else {
            page = new PageImpl<EOrder>(new ArrayList<EOrder>(),pageable.getPageable(),0);
        }
        return page;
    }

    
}
