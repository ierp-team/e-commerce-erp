package com.ierp.product.controller;

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
import com.ierp.product.domain.Product;
//import com.ierp.product.domain.ProductQueryDTO;
import com.ierp.product.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
//	@GetMapping
//	public Page<Product> getPage(ProductQueryDTO productQueryDTO , ExtjsPageRequest pageRequest){
//		return productService.findAll(ProductQueryDTO.getWhereClause(productQueryDTO), pageRequest.getPageable());
//	}
	
	@GetMapping(value="{id}")
	public Product getOne(@PathVariable("id") Long id) 
	{
		return productService.findById(id).get();
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) 
	{
		try {
			if(id!=null) {
				productService.deleteById(id);
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
				productService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("id") Long myId,@RequestBody Product dto) 
	{
		try {
			Product entity = productService.findById(myId).get();
			if(entity!=null) {
				BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
				productService.save(entity);
			}
			return new ExtAjaxResponse(true,"更新成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"更新失败！");
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody Product product) 
	{
		try {
			productService.save(product);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
	@RequestMapping("data")
	public String testData() {
		try {
			for(int i = 0; i < 10; i++) {
				Product product = new Product();
				product.setProduct_name("产品"+i);
				product.setProduct_price("￥"+i);
				
				productService.save(product);
			}
			return "success:true";
		} catch (Exception e) {
			return "success:false";
		}
	}
}
