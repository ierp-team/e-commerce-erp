package com.ierp.eordermodule.eorderproduct.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;

public interface IEOrderProductService {
    public List<EOrderProduct> getEOrderProductList(Long id);
    public Page<EOrderProduct> getEOrderProductPage(Long id,Pageable pageable);
//    
    public Page<EOrderProductDTO> getEOrderProductDTOList(Long id, Pageable pageable);
}
