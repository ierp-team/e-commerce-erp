package com.ierp.vendormodule.stockproduct.controller;

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

import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.vendormodule.stockorder.service.IStockOrderService;
import com.ierp.vendormodule.stockproduct.domain.StockProduct;
import com.ierp.vendormodule.stockproduct.domain.StockProductDisplayDTO;
import com.ierp.vendormodule.stockproduct.domain.StockProductQueryDTO;
import com.ierp.vendormodule.stockproduct.service.IStockProductService;

@RestController
@RequestMapping("/stockProduct")
public class StockProductController {

	@Autowired
	private IStockOrderService stockOrderService;
	@Autowired
	private IStockProductService stockProductService;
	
	@GetMapping
	public Page<StockProductDisplayDTO> getPage(Long stockOrderId, StockProductQueryDTO stockProductQueryDTO, ExtjsPageRequest pageRequest){
		System.out.println(stockOrderId);
		if(null != stockOrderId) {
			stockProductQueryDTO.setStockOrder(stockOrderService.findById(stockOrderId).get());
		}
		return stockProductService.findAll(stockProductQueryDTO.getWhereClause(stockProductQueryDTO), pageRequest.getPageable());
	}
	
	@GetMapping(value="{stockProductId}")
	public StockProduct getOne(@PathVariable("stockProductId") Long id) 
	{
		return stockProductService.findById(id).get();
	}
	
	@DeleteMapping(value="{stockProductId}")
	public ExtAjaxResponse delete(@PathVariable("stockProductId") Long id) 
	{
		try {
			if(id!=null) {
				stockProductService.deleteById(id);
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
				stockProductService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@PutMapping(value="{stockProductId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("stockProductId") Long myId,@RequestBody StockProduct dto) 
	{
		try {
			stockProductService.save(dto);
			return new ExtAjaxResponse(true,"更新成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"更新失败！");
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody StockProduct stockProduct) 
	{
		try {
			stockProductService.save(stockProduct);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
}
