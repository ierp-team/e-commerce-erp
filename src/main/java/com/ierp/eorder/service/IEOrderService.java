package com.ierp.eorder.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.eorder.domain.EOrder;

public interface IEOrderService {
    public EOrder  save(EOrder entity);
    public Optional<EOrder> findById(Long id);
    public boolean existsById(Long id);
    public long count();
    public void deleteById(Long id);
    public void deleteAll(Long[] ids);
    public Page<EOrder> findAll(Specification<EOrder> spec,Pageable pageable);
}
