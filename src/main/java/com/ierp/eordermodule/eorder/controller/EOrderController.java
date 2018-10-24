package com.ierp.eordermodule.eorder.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.ierp.eordermodule.eorder.domain.EOrderDTO;
import com.ierp.eordermodule.eorder.domain.EOrderQueryDTO;
import com.ierp.eordermodule.eorder.service.IEOrderService;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.service.IEOrderProductService;
import com.ierp.eordermodule.util.EOrderProductStatus;
import com.ierp.eordermodule.util.EOrderStatus;
import com.ierp.expressco.service.IExpresscoService;
import com.ierp.goods.domain.Goods;
import com.ierp.goods.service.IGoodsService;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

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
    private IEOrderProductService eOrderProductService;
    @Autowired 
    private IExpresscoService expresscoService;
    
    @GetMapping
    public Page<EOrderDTO>  getPage(EOrderQueryDTO eOrderQueryDTO , ExtjsPageRequest pageRequest) 
    {
        return eOrderService.queryEOrders(EOrderQueryDTO.getWhereClause(eOrderQueryDTO), pageRequest.getPageable());
    }
    
    @PostMapping
    public  ExtAjaxResponse save(@RequestBody EOrderDTO eOrderDTO) {
        try {
            eOrderDTO.setCreateTime(new Date());
            EOrder eOrder = new EOrder();
            EOrderDTO.dtoToEntity(eOrderDTO, eOrder);
            eOrder.setLogisticsCompany(expresscoService.findExpressco(eOrderDTO.getLogisticsCompany()));
            eOrderService.save(eOrder);
            return new ExtAjaxResponse(true,"操作成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ExtAjaxResponse(false,"操作失败!");
        }
    }
    @RequestMapping(value="/product")
    public  ExtAjaxResponse buildEOrder(@RequestBody String  eOrderJson) {
        try {
            EOrder eOrder = new EOrder();
            Float totalPrice = (float) 0;
            
            JSONObject ejson = JSONObject.parseObject(eOrderJson);
            
            //EOrderStatus orderStatus = EOrderStatus.ORIGINAL;
            
//            String address = ejson.getString("address");
//            System.out.println(address);
//            String contact = ejson.getString("contact");
//            Float freight = ejson.getFloat("freight");
//            String logisticsCompany = ejson.getString("logisticsCompany");
//            String phone = ejson.getString("phone");
//            String salePlatform = ejson.getString("salePlatform");
//            System.out.println(address+contact+freight+logisticsCompany+phone+salePlatform);
            
            eOrder.setAddress(ejson.getString("address"));
            eOrder.setContact( ejson.getString("contact"));
            eOrder.setCreateTime(new Date());
            eOrder.setFreight(ejson.getFloat("freight"));
            eOrder.setLogisticsCompany(expresscoService.findExpressco(ejson.getString("logisticsCompany")));
            eOrder.setPhone(ejson.getString("phone"));
            eOrder.setSalePlatform(ejson.getString("salePlatform"));
            eOrder.setOrderStatus(EOrderStatus.ORIGINAL);
            
            
            //List<EOrderProduct> eOrderProductList = new ArrayList<EOrderProduct>();
            JSONArray orderProducts = ejson.getJSONArray("list");
            for (int i = 0; i < orderProducts.size(); i++) {
                JSONObject jsonObject = orderProducts.getJSONObject(i);
                EOrderProduct eOrderProduct = new EOrderProduct();
                eOrderProduct.seteOrder(eOrder);
                
                Integer quantity = jsonObject.getInteger("quantity");
                Goods goods = goodsService.findByGoodsUuid(jsonObject.getString("goodsUuid")).get(0);
                
                eOrderProduct.setQuantity(quantity);
                eOrderProduct.setGood(goods);
                eOrderProduct.setOrderProductStatus(EOrderProductStatus.ORIGINAL);
                eOrderProduct.setTotalPrice(quantity*goods.getSalePrice());
                eOrderProductService.save(eOrderProduct);
                eOrder.getOrderProducts().add(eOrderProduct);
                
                totalPrice+=(goods.getSalePrice()*quantity);
                eOrder.setTotalPrice(totalPrice);
                eOrderService.save(eOrder);
            }
            return new ExtAjaxResponse(true,"操作成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ExtAjaxResponse(false,"操作失败!");
        }
    }
    
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
            }else if(eOrderService.findById(id).get().getOrderProducts()==null){
                return new ExtAjaxResponse(true,"该订单下没有产品!");
            }else{
                return new ExtAjaxResponse(true,"该订单有产品!");
            }   
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
//            Goods g1 = new Goods();
//            g1.setGoodsCode("qb0013");
//            g1.setGoodsName("铅笔13号");
//            g1.setGoodsDesc("13棒棒哒");
//            g1.setGoodsStock(130);
//            g1.setGoodsUuid("ppppp00013");
//            g1.setSupplyPrice((float)29.13);
//            g1.setSalePrice((float)135.2);
//            
//            Goods g2 = new Goods();
//            g2.setGoodsCode("qb0014");
//            g2.setGoodsName("铅笔14号");
//            g2.setGoodsDesc("14也是棒棒哒");
//            g2.setGoodsStock(140);
//            g2.setGoodsUuid("ppppp00014");
//            g2.setSupplyPrice((float)140.6);
//            g2.setSalePrice((float)214.8);
//            
//            EOrder eo1 = new EOrder();
//            eo1.setAddress("广州市松山湖大学路2号");
//            eo1.setContact("高先生学");
//            eo1.setCreateTime(new Date());
//            eo1.setLogisticsCompany(null);
//            eo1.setOrderNumber("tb000007");
//            eo1.setOrderStatus(EOrderStatus.ORIGINAL);
//            
//    
//            EOrderProduct eop1 = new EOrderProduct();
//            eop1.setGood(g1);
//            eop1.setQuantity(13);
//            eop1.setTotalPrice((float)1300);
//            eop1.seteOrder(eo1);
//            EOrderProduct eop2 = new EOrderProduct();
//            eop2.setGood(g2);
//            eop2.setQuantity(14);
//            eop2.setTotalPrice((float)1400);
//            eop2.seteOrder(eo1);
//            
//            goodsService.save(g1);
//            goodsService.save(g2);
//            eop1.setGood(g1);
//            eop2.setGood(g2);
//            eOrderProductService.save(eop1);
//            eOrderProductService.save(eop2);
//            eo1.getOrderProducts().add(eop1);
//            eo1.getOrderProducts().add(eop2);
//            eOrderService.save(eo1);
            
            
         
            return new ExtAjaxResponse(true,"操作成功！");
        } catch (Exception e) {
             e.printStackTrace();
             return new ExtAjaxResponse(false,"操作失败！");
        }
    } 

    
}
