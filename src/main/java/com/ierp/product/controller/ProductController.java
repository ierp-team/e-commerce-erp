package com.ierp.product.controller;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.ierp.common.beans.UpPic;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.product.domain.Product;
import com.ierp.product.domain.ProductDisplayDTO;
import com.ierp.product.domain.ProductQueryDTO;
import com.ierp.product.service.IProductService;
import com.ierp.vendor.service.IVendorService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private IVendorService vendorService;
	
	@GetMapping
	public Page<ProductDisplayDTO> getPage(/*String vendorAccount,*/ Long vendorId, ProductQueryDTO productQueryDTO , ExtjsPageRequest pageRequest){
		if(null!=vendorId) {
			productQueryDTO.setVendor(vendorService.findById(vendorId).get());
		}
		
//		if(null!=vendorAccount) {
//			productQueryDTO.setVendor(productService.findByVendorAccount(vendorAccount));
//		}
		return productService.findAll(ProductQueryDTO.getWhereClause(productQueryDTO), pageRequest.getPageable());
	}
	
	@GetMapping(value="{productId}")
	public Product getOne(@PathVariable("productId") Long id) 
	{
		return productService.findById(id).get();
	}
	
	@DeleteMapping(value="{productId}")
	public ExtAjaxResponse delete(@PathVariable("productId") Long id) 
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
	
//	@PutMapping(value="{productId}")
//	public ExtAjaxResponse update(@PathVariable("productId") Long myId,ProductDisplayDTO dto,MultipartFile productImage,HttpServletRequest request) 
//	{
//		try {
//				String productPic = null;
//				if(productImage!=null) {
//					productPic = UpPic.UpAndgetPath(productImage, request);
//				}
//				dto.setProductPic(productPic);
//				productService.save(dto);
//				return new ExtAjaxResponse(true,"更新成功！");
//		} catch (Exception e) {
//			return new ExtAjaxResponse(true,"更新失败！");
//		}
//	}
	
	@PostMapping
	public ExtAjaxResponse save(ProductDisplayDTO product,MultipartFile productImage,HttpServletRequest request) 
	{
		try {
			String productPic = null;
			if(productImage!=null) {
				productPic = UpPic.UpAndgetPath(productImage, request);
			}
			product.setProductPic(productPic);
			productService.save(product);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
}
