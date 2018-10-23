package com.ierp.logistics.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.logistics.domain.Logistics;
import com.ierp.logistics.domain.LogisticsRequestDTO;
import com.ierp.logistics.domain.LogisticsDTO;

public interface ILogisticsService {
	
	public Logistics save(LogisticsRequestDTO dto);
	public Logistics update(LogisticsDTO dto);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public Optional<Logistics> findById(Long id);
	public Page<LogisticsDTO> findAll(Specification<Logistics> spec, Pageable pageable);
	public boolean existsById(Long id);
	public long count();
	
}
