package com.ierp.goods.controller;

import java.util.List;

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

import com.ierp.goods.domain.GoodsQueryDTO;
import com.ierp.common.beans.PhotoUploadUtils;
import com.ierp.common.web.ExtAjaxResponse;
import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.goods.domain.Goods;
import com.ierp.goods.domain.GoodsDTO;
import com.ierp.goods.service.GoodsService;
import com.ierp.vendormodule.product.domain.ProductDisplayDTO;

@RestController
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	GoodsService goodsService;
	
	@PostMapping
	public ExtAjaxResponse save(GoodsDTO dto, MultipartFile photoFile, HttpServletRequest request) {
		try {
			String goodsPhoto = null;
			if (photoFile!=null) {
				goodsPhoto = PhotoUploadUtils.UpAndgetPath(photoFile, request);
			}
			dto.setGoodsPhoto(goodsPhoto);
			goodsService.save(dto);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch(Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
	//从供应商产品表里添加产品到公司商品表
	@PostMapping(value="/fromproduct")
	public ExtAjaxResponse saveFromProduct(ProductDisplayDTO dto) {
		try {
			GoodsDTO goodsDto = new GoodsDTO();
			goodsDto.setGoodsName(dto.getProductName());
			goodsDto.setGoodsPhoto(dto.getProductPic());
			goodsDto.setGoodsUuid(dto.getProductUuid());
			goodsDto.setGoodsDesc(dto.getProductDesc());
			goodsDto.setGoodsSpec(dto.getProductSpec());
			goodsDto.setVendorId(dto.getVendorId());
			goodsDto.setSupplyPrice(dto.getProductPrice());
			goodsDto.setSalePrice(dto.getProductPrice()*2);
			goodsDto.setGoodsStock(0);
			goodsService.save(goodsDto);
			return new ExtAjaxResponse(true,"保存成功！");
		} catch(Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
	@PostMapping(value="/update")
	public ExtAjaxResponse update(GoodsDTO dto, MultipartFile photoFile, HttpServletRequest request) {
		try {
			String goodsPhoto = null;
			Goods entity = goodsService.findById(dto.getId()).get();
			if(entity!=null) {
				//BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
				if (photoFile.isEmpty()) {
					goodsPhoto = entity.getGoodsPhoto();
				} else {
					goodsPhoto = PhotoUploadUtils.UpAndgetPath(photoFile, request);
				}
				dto.setGoodsPhoto(goodsPhoto);
				goodsService.update(dto);
				return new ExtAjaxResponse(true,"保存成功！");
			} else {
				return new ExtAjaxResponse(true,"保存失败！");
			}
		} catch(Exception e) {
			return new ExtAjaxResponse(true,"保存失败！");
		}
	}
	
//	@PutMapping(value="{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
//	public ExtAjaxResponse update(@PathVariable("id") Long id,@RequestBody GoodsDTO dto) {
//		try {
//			Goods entity = goodsService.findById(id).get();
//			if(entity!=null) {
//				//BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
//				goodsService.update(dto);
//				return new ExtAjaxResponse(true,"保存成功！");
//			} else {
//				return new ExtAjaxResponse(true,"保存失败！");
//			}
//		} catch(Exception e) {
//			return new ExtAjaxResponse(true,"保存失败！");
//		}
//	}
	
	@DeleteMapping(value="{id}")
	public ExtAjaxResponse delete(@PathVariable("id") Long id) {
		try {
			if(id!=null) {
				goodsService.deleteById(id);
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
				goodsService.deleteAll(ids);
				return new ExtAjaxResponse(true,"批量删除成功！");
			} else {
				return new ExtAjaxResponse(true,"批量删除失败！");
			}
		} catch (Exception e) {
			return new ExtAjaxResponse(true,"批量删除失败！");
		}
	}
	
	@GetMapping(value="{id}")
	public Goods getOne(@PathVariable("id") Long id) {
		return goodsService.findById(id).get();
	}
	
	@RequestMapping(value="/getlist")
    public List<Goods> getList() {
        return goodsService.findAll();
    }
	
	@GetMapping
	public Page<GoodsDTO> getPage(GoodsQueryDTO goodsQueryDTO , ExtjsPageRequest pageRequest) {
		return goodsService.findAll(GoodsQueryDTO.getWhereClause(goodsQueryDTO), pageRequest.getPageable());
	}
	
}
