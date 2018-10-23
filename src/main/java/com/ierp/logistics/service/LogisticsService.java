package com.ierp.logistics.service;

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

import com.ierp.common.beans.BeanUtils;
import com.ierp.expressco.domain.Expressco;
import com.ierp.expressco.service.IExpresscoService;
import com.ierp.logistics.domain.Logistics;
import com.ierp.logistics.domain.LogisticsDTO;
import com.ierp.logistics.domain.LogisticsRequestDTO;
import com.ierp.logistics.repository.LogisticsRepository;
import com.ierp.logistics.util.KdApiEOrderUtil;

@Service
public class LogisticsService implements ILogisticsService {

	@Autowired
	LogisticsRepository logisticsRepository;
	
	@Autowired
	IExpresscoService expresscoService;
	
	@Override
	public Logistics save(LogisticsRequestDTO dto) {
		try {
			Logistics entity = new Logistics();
			String expressNumber = KdApiEOrderUtil.addOrderOnline(dto);
			
			LogisticsRequestDTO.dtoToEntity(dto, entity);
			
			Expressco expressco = expresscoService.findByExpresscoCode(dto.getExpresscoCode());
			if (null!=expressco) {
				entity.setLogisticsCompany(expressco);
			}
			entity.setExpressNumber(expressNumber);
			
			return logisticsRepository.save(entity);
		} catch(Exception e) {
			return null;
		}
	}
	
	@Override
	public Logistics update(LogisticsDTO dto) {
		try {
			Logistics entity= findById(dto.getId()).get();
			
			BeanUtils.copyProperties(dto, entity);//使用自定义的BeanUtils
			
			Expressco expressco = expresscoService.findByExpresscoCode(dto.getExpresscoCode());
			if (null!=expressco) {
				entity.setLogisticsCompany(expressco);
			}
			
			return logisticsRepository.save(entity);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public void deleteById(Long id) {
		logisticsRepository.deleteById(id);
	}

	@Override
	public void deleteAll(Long[] ids) {
		List<Long> idLists = new ArrayList<Long>(Arrays.asList(ids));
		
		List<Logistics> logisticsList = (List<Logistics>) logisticsRepository.findAllById(idLists);
		if(logisticsList!=null) {
			logisticsRepository.deleteAll(logisticsList);
		}
	}
	
	@Override
	public Optional<Logistics> findById(Long id) {
		return logisticsRepository.findById(id);
	}

	@Override
	public Page<LogisticsDTO> findAll(Specification<Logistics> spec, Pageable pageable) {
		Page<Logistics> entityPage = logisticsRepository.findAll(spec, pageable);
		List<Logistics> entityLists = entityPage.getContent();
		List<LogisticsDTO> dtoLists = null;
		if(entityLists!=null) {
			dtoLists = new ArrayList<LogisticsDTO>();
			for(Logistics entity : entityLists) {
				LogisticsDTO dto = new LogisticsDTO();
				LogisticsDTO.entityToDto(entity, dto);
				dtoLists.add(dto);
			}
		}
		return new PageImpl<LogisticsDTO>(dtoLists, pageable, entityPage.getTotalElements());
	}

	@Override
	public boolean existsById(Long id) {
		return logisticsRepository.existsById(id);
	}

	@Override
	public long count() {
		return logisticsRepository.count();
	}
	
}
