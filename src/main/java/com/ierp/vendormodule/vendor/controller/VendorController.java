package com.ierp.vendormodule.vendor.controller;

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
import com.ierp.permissionmodule.group.domain.Group;
import com.ierp.permissionmodule.group.service.IGroupService;
import com.ierp.permissionmodule.user.domain.User;
import com.ierp.permissionmodule.user.service.IUserService;
import com.ierp.vendormodule.vendor.domain.Vendor;
import com.ierp.vendormodule.vendor.domain.VendorDTO;
import com.ierp.vendormodule.vendor.domain.VendorQueryDTO;
import com.ierp.vendormodule.vendor.service.IVendorService;

@RestController
@RequestMapping("/vendor")
public class VendorController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private IVendorService vendorService;
	
	@GetMapping
	public Page<Vendor> getPage(VendorQueryDTO vendorQueryDTO , ExtjsPageRequest pageRequest){
		return vendorService.findAll(VendorQueryDTO.getWhereClause(vendorQueryDTO), pageRequest.getPageable());
	}
	
	@GetMapping(value="{vendorId}")
	public Vendor getOne(@PathVariable("vendorId") Long id) 
	{
		return vendorService.findById(id).get();
	}
	
	@DeleteMapping(value="{vendorId}")
	public ExtAjaxResponse delete(@PathVariable("vendorId") Long id) 
	{
		try {
			if(id!=null) {
				vendorService.deleteById(id);
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
				vendorService.deleteAll(ids);
			}
			return new ExtAjaxResponse(true,"批量删除成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@PutMapping(value="{vendorId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("vendorId") Long myId,@RequestBody Vendor dto) 
	{
		try {
			Vendor entity = vendorService.findById(myId).get();
			if(entity!=null) {
				BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
				vendorService.save(entity);
			}
			return new ExtAjaxResponse(true,"更新成功！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"更新失败！");
		}
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody VendorDTO vendordto) 
	{
		try {
			if(vendorService.findByVendorName(vendordto.getVendorName())==null) {
				User user = new User();
				user.setId(vendordto.getVendorAccount());
				user.setPassword(vendordto.getVendorPassword());
				Group group = groupService.findById("suplier").get();
				user.getGroup().add(group);
				userService.save(user);
				Vendor entity = new Vendor();
				vendordto.dtoToEntity(vendordto, entity);
				entity.setUser(user);
				vendorService.save(entity);
				return new ExtAjaxResponse(true,"保存成功！");
			}else
				return new ExtAjaxResponse(true,"保存失败！");
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
}
