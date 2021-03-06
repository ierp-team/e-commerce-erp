package com.ierp.eordermodule.eorder.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;

import com.ierp.eordermodule.eorder.domain.EOrder;
import com.ierp.eordermodule.eorder.domain.EOrderQueryDTO;
import com.ierp.eordermodule.util.EOrderStatus;

public class EOrderQueryDTO {
    
        private Date startTime_from;
        private Date startTime_to;
        private String phone;
        private String expressNumber;
        private EOrderStatus orderStatus;
       
        
        
        public EOrderStatus getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(EOrderStatus orderStatus) {
            this.orderStatus = orderStatus;
        }

        @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
        public Date getStartTime_from() {
            return startTime_from;
        }

        public void setStartTime_from(Date startTime_from) {
            this.startTime_from = startTime_from;
        }
        
        @DateTimeFormat(pattern="yyyy/MM/dd HH:mm:ss")  
        public Date getStartTime_to() {
            return startTime_to;
        }

        public void setStartTime_to(Date startTime_to) {
            this.startTime_to = startTime_to;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getExpressNumber() {
            return expressNumber;
        }

        public void setExpressNumber(String expressNumber) {
            this.expressNumber = expressNumber;
        }

    @SuppressWarnings({ "serial"})
    public static Specification<EOrder> getWhereClause(final EOrderQueryDTO leaveQueryDTO) {
        return new Specification<EOrder>() {
            @Override
            public Predicate toPredicate(Root<EOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            
                List<Predicate> predicate = new ArrayList<>();

                if (null!=leaveQueryDTO.getStartTime_from()) {
                    predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class),
                            leaveQueryDTO.getStartTime_from()));
                }
                
                if (null!=leaveQueryDTO.getStartTime_to()) {
                    predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class),
                            leaveQueryDTO.getStartTime_to()));
                }
                
                if (leaveQueryDTO.getPhone()!=""&&leaveQueryDTO.getPhone()!=null) {
                    predicate.add(criteriaBuilder.equal(root.get("phone").as(String.class),
                            leaveQueryDTO.getPhone()));
                }
                
                if (leaveQueryDTO.getExpressNumber()!=""&&leaveQueryDTO.getPhone()!=null) {
                    predicate.add(criteriaBuilder.equal(root.get("expressNumber").as(String.class),
                            leaveQueryDTO.getExpressNumber()));
                }
                
                if (null!=leaveQueryDTO.getOrderStatus()) {
                    predicate.add(criteriaBuilder.equal(root.get("orderStatus").as(String.class),
                            leaveQueryDTO.getOrderStatus().toString()));
                }
                
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).getRestriction();
            }
        };
    }
}
