package com.ierp.stockorder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.common.beans.BeanUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.stockorder.domain.StockOrder;
import com.ierp.stockorder.domain.StockOrderQueryDTO;
import com.ierp.stockorder.service.IStockOrderService;

@RestController
@RequestMapping("/stockOrder")
public class StockOrderController {
	
	@Autowired
	private IStockOrderService stockOrderService;
	
	@GetMapping
	public Page<StockOrder> getPage(StockOrderQueryDTO stockOrderQueryDTO , ExtjsPageRequest pageRequest){
		return stockOrderService.findAll(StockOrderQueryDTO.getWhereClause(stockOrderQueryDTO), pageRequest.getPageable());
	}
	
	@GetMapping(value="{stockOrderId}")
	public StockOrder getOne(@PathVariable("stockOrderId") Long id) 
	{
		return stockOrderService.findById(id).get();
	}
	
	@DeleteMapping(value="{stockOrderId}")
	public ExtAjaxResponse delete(@PathVariable("stockOrderId") Long id) 
	{
		try {
			if(id!=null) {
				stockOrderService.deleteById(id);
			}
			return new ExtAjaxResponse(true,"删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) 
	{
		try {
			if(ids!=null) {
				stockOrderService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@PutMapping(value="{stockOrderId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("stockOrderId") Long myId,@RequestBody StockOrder dto) 
	{
		try {
			StockOrder entity = stockOrderService.findById(myId).get();
			if(entity!=null) {
				BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
				stockOrderService.save(entity);
			}
			return new ExtAjaxResponse(true,"更新成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"更新失败！");
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody StockOrder stockOrder) 
	{
		try {
				return new ExtAjaxResponse(true,"保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
}
