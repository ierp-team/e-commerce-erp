package com.ierp.logistics.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.logistics.domain.Logistics;
import com.ierp.logistics.domain.LogisticsRequestDTO;
import com.ierp.logistics.domain.LogisticsResponseDTO;

public interface ILogisticsService {
	
	public Logistics save(LogisticsRequestDTO dto);
	
	public void deleteById(Long id);
	public void deleteAll(Long[] ids);
	
	public Optional<Logistics> findById(Long id);
	public Page<LogisticsResponseDTO> findAll(Specification<Logistics> spec, Pageable pageable);
	public boolean existsById(Long id);
	public long count();
	
}
