package com.ierp.eordermodule.eorderproduct.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;

public interface IEOrderProductService {
    public List<EOrderProduct> getEOrderProductList(Long id);
    
    public Page<EOrderProductDTO> getEOrderProductDTOList(Long id, ExtjsPageRequest pageRequest);
}
