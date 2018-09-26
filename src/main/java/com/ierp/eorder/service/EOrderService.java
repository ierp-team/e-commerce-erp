package com.ierp.eorder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ierp.eorder.domain.EOrder;
import com.ierp.eorder.repository.IEOrderRepository;

/**
 * @author ghb, Date:Sep 26, 20183:00:24 PM
 *
 */
@Service
@Transactional
public class EOrderService implements IEOrderService {
    
    @Autowired
    IEOrderRepository eOrderRepository;

    @Override
    public EOrder save(EOrder entity) {
        return eOrderRepository.save(entity);
    }

    @Override
    @Transactional
    public Optional<EOrder> findById(Long id) {
        return eOrderRepository.findById(id);
    }

    @Override
    @Transactional
    public boolean existsById(Long id) {
        return eOrderRepository.existsById(id);
    }

    @Override
    @Transactional
    public long count() {
        return eOrderRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        eOrderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll(Long[] ids) {
        List<Long> idlist = new ArrayList<Long>(Arrays.asList(ids));
        List<EOrder> eOrderList =  (List<EOrder>)eOrderRepository.findAllById(idlist);
        if(eOrderList!=null) {
            eOrderRepository.deleteAll(eOrderList);
        }
    }

    @Override
    @Transactional
    public Page<EOrder> findAll(Specification<EOrder> spec, Pageable pageable) {
        return eOrderRepository.findAll(spec, pageable);
    }

    
}
