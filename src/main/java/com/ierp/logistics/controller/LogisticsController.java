package com.ierp.logistics.controller;

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

import com.ierp.logistics.domain.LogisticsQueryDTO;
//import com.ierp.common.beans.BeanUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.logistics.domain.Logistics;
import com.ierp.logistics.domain.LogisticsRequestDTO;
import com.ierp.logistics.domain.LogisticsDTO;
import com.ierp.logistics.service.LogisticsService;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {
	
	@Autowired
	LogisticsService logisticsService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody LogisticsRequestDTO dto) {
		try {
			logisticsService.save(dto);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch(Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("id") Long id,@RequestBody LogisticsDTO dto) {
		try {
			Logistics entity = logisticsService.findById(id).get();
			if(entity!=null) {
				//BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
				logisticsService.update(dto);
				return new ExtAjaxResponse(true,"保存成功！");
			} else {
				return new ExtAjaxResponse(true,"保存失败！");
			}
		} catch(Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			if(id!=null) {
				logisticsService.deleteById(id);
				return new ExtAjaxResponse(true,"删除成功！");
			} else {
				return new ExtAjaxResponse(true,"删除失败！");
			}
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="ids") Long[] ids) {
		try {
			if(ids!=null) {
				logisticsService.deleteAll(ids);
				return new ExtAjaxResponse(true,"批量删除成功！");
			} else {
				return new ExtAjaxResponse(true,"批量删除失败！");
			}
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@GetMapping(value="{id}")
	public Logistics getOne(@PathVariable("id") Long id) {
		return logisticsService.findById(id).get();
	}
	
	@GetMapping
	public Page<LogisticsDTO> getPage(LogisticsQueryDTO logisticsQueryDTO , ExtjsPageRequest pageRequest) {
		return logisticsService.findAll(LogisticsQueryDTO.getWhereClause(logisticsQueryDTO), pageRequest.getPageable());
	}
	
}
