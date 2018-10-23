package com.ierp.eordermodule.eorderproduct.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;
import com.ierp.goods.domain.Goods;

public interface IEOrderProductService {

    
    public EOrderProduct save(EOrderProduct entity);
    
    public void deleteById(Long id);
    public void deleteAll(Long[] ids);
    
    public Optional<EOrderProduct> findById(Long id);
    public Page<EOrderProduct> findAll(Specification<EOrderProduct> spec, Pageable pageable);
    public boolean existsById(Long id);
    public long count();
    public List<EOrderProduct> findAll();
    
       
    public List<EOrderProduct> getEOrderProductList(Long id);
    public Page<EOrderProduct> getEOrderProductPage(Long id,Pageable pageable);
//    
    public Page<EOrderProductDTO> getEOrderProductDTOList(Long id, Pageable pageable);
}
