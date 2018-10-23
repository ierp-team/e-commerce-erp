package com.ierp.eordermodule.eorderproduct.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.common.web.ExtjsPageRequest;
import com.ierp.eordermodule.eorder.domain.EOrderManageDTO;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProduct;
import com.ierp.eordermodule.eorderproduct.domain.EOrderProductDTO;
import com.ierp.eordermodule.eorderproduct.repository.IEOrderProductRepository;
import com.ierp.goods.domain.Goods;
import com.ierp.goods.repository.GoodsRepository;
@Service
@Transactional
public class EOrderProductService implements IEOrderProductService {

    @Autowired
    private IEOrderProductRepository eOrderProductRepository;
    
    
    @Override
    public EOrderProduct save(EOrderProduct entity) {
        return eOrderProductRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        eOrderProductRepository.deleteById(id);

    }

    @Override
    public void deleteAll(Long[] ids) {
        List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
        
        List<EOrderProduct> goodsList = (List<EOrderProduct>) eOrderProductRepository.findAllById(idLists);
        if(goodsList!=null) {
            eOrderProductRepository.deleteAll(goodsList);
        }
    }
    
    @Override
    public Optional<EOrderProduct> findById(Long id) {
        return eOrderProductRepository.findById(id);
    }

    @Override
    public Page<EOrderProduct> findAll(Specification<EOrderProduct> spec, Pageable pageable) {
        return eOrderProductRepository.findAll(spec, pageable);
    }

    @Override
    public boolean existsById(Long id) {
        return eOrderProductRepository.existsById(id);
    }

    @Override
    public long count() {
        return eOrderProductRepository.count();
    }
    
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

    @Override
    public List<EOrderProduct> findAll() {
        return (List<EOrderProduct>) eOrderProductRepository.findAll();
    }
}
