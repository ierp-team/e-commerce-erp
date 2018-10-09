package com.ierp.eordermodule.eorderproduct.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;
import com.ierp.eordermodule.eorderproduct.repository.IEOrderProductRepository;

public class EOrderProductService implements IEOrderProductService {

    @Autowired
    private IEOrderProductRepository eOrderProductRepository;
    
    @Override
    public List<EOrderProduct> getEOrderProductList(Long id) {
        return eOrderProductRepository.findEOrderProducts(id);
    }

    @Override
    public Page<EOrderProductDTO> getEOrderProductDTOList(Long id, ExtjsPageRequest pageRequest) {
        List <EOrderProduct>  eOrderProductList  = eOrderProductRepository.findEOrderProducts(id);
        if(id!=null) {
            List <EOrderProductDTO>  eOrderProductDTOList = new ArrayList<EOrderProductDTO>();
            for(EOrderProduct eOrderProduct: eOrderProductList){
                EOrderProductDTO eOrderProductDTO = new EOrderProductDTO();
                EOrderProductDTO.entityToDto(eOrderProduct, eOrderProductDTO);
                eOrderProductDTOList.add(eOrderProductDTO);
            }
            return new PageImpl<EOrderProductDTO>(eOrderProductDTOList, pageRequest.getPageable(), eOrderProductList.size());
        }else{
            return new PageImpl<EOrderProductDTO>(new ArrayList<EOrderProductDTO>(), pageRequest.getPageable(), 0);
        }
    }
}
