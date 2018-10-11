package com.ierp.eordermodule.eorder.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorder.domain.EOrderQueryDTO;
import com.ierp.eordermodule.eorder.service.IEOrderService;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.repository.IEOrderProductRepository;
import com.ierp.goods.domain.Goods;
import com.ierp.goods.service.IGoodsService;
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.navigation.domain.NavigationNode;
import com.ierp.common.beans.BeanUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;


@RestController
@RequestMapping(value="/eorder")
public class EOrderController {

    @Autowired 
    private IEOrderService eOrderService;
    @Autowired 
    private IGoodsService goodsService;
    @Autowired 
    private IEOrderProductRepository eOrderProductRepository;
    
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
    @RequestMapping(value="/isexist/{id}")
    public ExtAjaxResponse isExist(@PathVariable("id") Long id) {
        try {

                if(!(eOrderService.existsById(id))||id==null){
                    return new ExtAjaxResponse(false,"订单不存在!");
                }
                return new ExtAjaxResponse(true,"订单存在!");

            
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
    
//    @GetMapping
//    public Page<EOrder> getEOrderList(@RequestParam String orderStatus,ExtjsPageRequest pageable) {
//        Page<EOrder> page;//接收结果集
//        if(orderStatus!=null) {
//            page = eOrderService.getEOrders(orderStatus,pageable.getPageable());
//        }else {
//            page = new PageImpl<EOrder>(new ArrayList<EOrder>(),pageable.getPageable(),0);
//        }
//        return page;
//    }
    @RequestMapping("/init")
    public @ResponseBody ExtAjaxResponse initData() 
    { 
        try {
            Goods g1 = new Goods();
            g1.setGoodsCode("qb007");
            g1.setGoodsName("铅笔7号");
            g1.setGoodsDesc("7棒棒哒");
            g1.setGoodsStock(70);
            g1.setGoodsUuid("ppppp0007");
            g1.setSupplyPrice((float)27.6);
            g1.setSalePrice((float)25.7);
            
            Goods g2 = new Goods();
            g2.setGoodsCode("qb008");
            g2.setGoodsName("铅笔8号");
            g2.setGoodsDesc("8也是棒棒哒");
            g2.setGoodsStock(80);
            g2.setGoodsUuid("ppppp0008");
            g2.setSupplyPrice((float)18.6);
            g2.setSalePrice((float)28.8);
            
            EOrder eo1 = new EOrder();
            eo1.setAddress("东莞市松山湖大学路1号");
            eo1.setContact("高同学");
            eo1.setCreateTime(new Date());
            eo1.setLogisticsCompany(null);
            eo1.setOrderNumber("tb000003");
            
    
            EOrderProduct eop1 = new EOrderProduct();
            eop1.setGood(g1);
            eop1.setQuantity(2);
            eop1.setTotalPrice((float)700);
            eop1.seteOrder(eo1);
            EOrderProduct eop2 = new EOrderProduct();
            eop2.setGood(g2);
            eop2.setQuantity(3);
            eop2.setTotalPrice((float)800);
            eop2.seteOrder(eo1);
            
            goodsService.save(g1);
            goodsService.save(g2);
            eop1.setGood(g1);
            eop2.setGood(g2);
            eOrderProductRepository.save(eop1);
            eOrderProductRepository.save(eop2);
            eo1.getOrderProducts().add(eop1);
            eo1.getOrderProducts().add(eop2);
            eOrderService.save(eo1);
            
            
         
            return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    } 

    
}
