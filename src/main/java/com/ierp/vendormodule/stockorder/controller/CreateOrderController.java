package com.ierp.vendormodule.stockorder.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ierp.vendormodule.product.service.IProductService;
import com.ierp.vendormodule.stockorder.domain.StockOrder;
import com.ierp.vendormodule.stockorder.domain.StockOrderStatus;
import com.ierp.vendormodule.stockorder.service.IStockOrderService;
import com.ierp.vendormodule.stockproduct.domain.StockProduct;
import com.ierp.vendormodule.stockproduct.service.IStockProductService;

@Controller
@RequestMapping("/createOrder")
public class CreateOrderController {

	private StockOrder stockOrder;
	private StockProduct stockProduct;
	private Float stockOrderSum = (float) 0;

	@Autowired
	private IStockOrderService stockOrderService;
	@Autowired
	private IStockProductService stockProductService;
	@Autowired
	private IProductService productService;
	
	@RequestMapping(value = "/saveDetail",method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public void saveDetail(@RequestBody String jString) {
		System.out.println(jString);
		JSONObject root = new JSONObject().parseObject(jString);
		stockOrder.setStockOrderNumber(root.getString("stockOrderNumber"));
		stockOrder.setUserName(root.getString("userName"));
		stockOrder.setPhoneNumber(root.getString("phoneNumber"));
		stockOrder.setStockOrderStatus(StockOrderStatus.ORIGINAL);
		stockOrder.setCreateTime(new Date());
		stockOrder.setStockOrderSum(stockOrderSum);
		stockOrderService.save(stockOrder);
		
		//保存详细
		JSONArray list = root.getJSONArray("list");
		if(null!=list) {
			for (int i = 0; i < list.size(); i++) {
				JSONObject detailBean = (JSONObject) list.get(i);
				stockProduct.setProduct(productService.findById(detailBean.getLong("productId")).get());
				stockProduct.setStockOrder(stockOrderService.findByStockOrderNumber(root.getString("stockOrderNumber")));
				stockProduct.setStockProductQuan(detailBean.getInteger("stockProductQuan"));
				stockProduct.setStockProductAmmount(detailBean.getFloat("stockProductAmmount"));
				stockOrderSum += detailBean.getFloat("stockProductAmmount");
				stockProductService.save(stockProduct);
			}
		}
	}
}
