package com.ierp.expressco.controller;

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

import com.ierp.expressco.domain.ExpresscoQueryDTO;
import com.ierp.common.beans.BeanUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.expressco.domain.Expressco;
import com.ierp.expressco.service.ExpresscoService;

@RestController
@RequestMapping("/expressco")
public class ExpresscoController {
	
	@Autowired
	ExpresscoService expresscoService;
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse save(@RequestBody Expressco expressco) {
		try {
			expresscoService.save(expressco);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch(Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
	@PutMapping(value="{expresscoId}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ExtAjaxResponse update(@PathVariable("expresscoId") Long expresscoId,@RequestBody Expressco dto) {
		try {
			Expressco entity = expresscoService.findById(expresscoId).get();
			if(entity!=null) {
				BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
				expresscoService.save(entity);
				return new ExtAjaxResponse(true,"保存成功！");
			} else {
				return new ExtAjaxResponse(true,"保存失败！");
			}
		} catch(Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
	@DeleteMapping(value="{expresscoId}")
	public ExtAjaxResponse delete(@PathVariable("expresscoId") Long expresscoId) {
		try {
			if(expresscoId!=null) {
				expresscoService.deleteById(expresscoId);
				return new ExtAjaxResponse(true,"删除成功！");
			} else {
				return new ExtAjaxResponse(true,"删除失败！");
			}
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"删除失败！");
		}
	}
	
	@PostMapping("/deletes")
	public ExtAjaxResponse deleteRows(@RequestParam(name="expresscoIds") Long[] expresscoIds) {
		try {
			if(expresscoIds!=null) {
				expresscoService.deleteAll(expresscoIds);
				return new ExtAjaxResponse(true,"批量删除成功！");
			} else {
				return new ExtAjaxResponse(true,"批量删除失败！");
			}
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@GetMapping(value="{expresscoId}")
	public Expressco getOne(@PathVariable("expresscoId") Long expresscoId) {
		return expresscoService.findById(expresscoId).get();
	}
	
	@GetMapping
	public Page<Expressco> getPage(ExpresscoQueryDTO expresscoQueryDTO , ExtjsPageRequest pageRequest) {
		return expresscoService.findAll(expresscoQueryDTO.getWhereClause(expresscoQueryDTO), pageRequest.getPageable());
	}
	
}
