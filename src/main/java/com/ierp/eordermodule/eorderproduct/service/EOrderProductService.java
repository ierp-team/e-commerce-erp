package com.ierp.eordermodule.eorderproduct.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.eordermodule.eorder.domain.EOrderDTO;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;
import com.ierp.eordermodule.eorderproduct.repository.IEOrderProductRepository;
@Service
@Transactional
public class EOrderProductService implements IEOrderProductService {

    @Autowired
    private IEOrderProductRepository eOrderProductRepository;
    
    @Override
    public List<EOrderProduct> getEOrderProductList(Long id) {
        return eOrderProductRepository.findEOrderProducts(id);
    }

    @Override
    public Page<EOrderProduct> getEOrderProductPage(Long id,Pageable pageable) {
        List <EOrderProduct> eOrderProductList = eOrderProductRepository.findEOrderProducts(id);
        return new PageImpl<EOrderProduct> (eOrderProductList, pageable, null!=eOrderProductList?eOrderProductList.size():0);
    }

    @Override
    public Page<EOrderProductDTO> getEOrderProductDTOList(Long id, Pageable pageable) {
        List <EOrderProduct>  eOrderProductList  = eOrderProductRepository.findEOrderProducts(id);
        if(id!=null) {
            List <EOrderProductDTO>  eOrderProductDTOList = new ArrayList<EOrderProductDTO>();
            for(EOrderProduct eOrderProduct: eOrderProductList){
                EOrderProductDTO eOrderProductDTO = new EOrderProductDTO();
                EOrderProductDTO.entityToDto(eOrderProduct, eOrderProductDTO);
                eOrderProductDTOList.add(eOrderProductDTO);
            }
            return new PageImpl<EOrderProductDTO>(eOrderProductDTOList,pageable, eOrderProductList.size());
        }else{
            return new PageImpl<EOrderProductDTO>(new ArrayList<EOrderProductDTO>(),pageable, 0);
        }
    }
}
