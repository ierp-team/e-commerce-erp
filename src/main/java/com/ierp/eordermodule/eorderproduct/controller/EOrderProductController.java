package com.ierp.eordermodule.eorderproduct.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.common.web.SessionUtil;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;
import com.ierp.eordermodule.eorderproduct.service.IEOrderProductService;

@RestController
@RequestMapping(value="/eorderproduct")
public class EOrderProductController {
    @Autowired 
    private IEOrderProductService eOrderProductService;
    
//    @GetMapping
//    public Page<EOrderProductDTO>  getEOrderProductDTOPage(@RequestParam(name="id") Long id, ExtjsPageRequest pageRequest) 
//    {
//        return eOrderProductService.getEOrderProductDTOList(id, pageRequest);
//    }
    @RequestMapping(value = "/getlist")
    public  Page<EOrderProductDTO> findEOrderProducts(@RequestParam(name="id") Long id, ExtjsPageRequest pageable) {
        Page<EOrderProductDTO> page = new PageImpl<EOrderProductDTO>(new ArrayList<EOrderProductDTO>(), pageable.getPageable(), 0);
        try {
            page = eOrderProductService.getEOrderProductDTOList(id ,pageable.getPageable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
    
    
    
   
}
