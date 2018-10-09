package com.ierp.eordermodule.eorderproduct.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ierp.common.web.ExtjsPageRequest;

import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;
import com.ierp.eordermodule.eorderproduct.service.IEOrderProductService;

@RestController
@RequestMapping(value="/eorderproduct")
public class EOrderProductController {
    @Autowired 
    private IEOrderProductService eOrderProductService;
    
    @GetMapping
    public Page<EOrderProductDTO>  getEOrderProductDTOPage(@RequestParam(name="id") Long id, ExtjsPageRequest pageRequest) 
    {
        return eOrderProductService.getEOrderProductDTOList(id, pageRequest);
    }
}
