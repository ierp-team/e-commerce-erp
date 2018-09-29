//package com.ierp.product.domain;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.data.jpa.domain.Specification;
//
//import com.ierp.vendor.domain.Vendor;
//
//
//public class ProductQueryDTO {
//
////	private Vendor vendor;
//	private String product_name;
//	private String product_price;
//	private String product_desc;
//	private String product_pic;	  //图片路径
//	private String product_spec;  //规格
//	private String product_uuid;  //唯一识别号
//	private String product_status;//状态  1.存货  2.缺货  3.下架
//	private Integer product_stock; //库存
//	
//
////	public Vendor getVendor() {
////		return vendor;
////	}
//	public String getProduct_name() {
//		return product_name;
//	}
//	public String getProduct_price() {
//		return product_price;
//	}
//	public String getProduct_desc() {
//		return product_desc;
//	}
//	public String getProduct_pic() {
//		return product_pic;
//	}
//	public String getProduct_spec() {
//		return product_spec;
//	}
//	public String getProduct_uuid() {
//		return product_uuid;
//	}
//	public String getProduct_status() {
//		return product_status;
//	}
//	public Integer getProduct_stock() {
//		return product_stock;
//	}
//	
//	
//	
////	public void setVendor(Vendor vendor) {
////		this.vendor = vendor;
////	}
//	public void setProduct_name(String product_name) {
//		this.product_name = product_name;
//	}
//	public void setProduct_price(String product_price) {
//		this.product_price = product_price;
//	}
//	public void setProduct_desc(String product_desc) {
//		this.product_desc = product_desc;
//	}
//	public void setProduct_pic(String product_pic) {
//		this.product_pic = product_pic;
//	}
//	public void setProduct_spec(String product_spec) {
//		this.product_spec = product_spec;
//	}
//	public void setProduct_uuid(String product_uuid) {
//		this.product_uuid = product_uuid;
//	}
//	public void setProduct_status(String product_status) {
//		this.product_status = product_status;
//	}
//	public void setProduct_stock(Integer product_stock) {
//		this.product_stock = product_stock;
//	}
//	
//	@SuppressWarnings({ "serial"})
//	public static Specification<Product> getWhereClause(final ProductQueryDTO productQueryDTO) {
//		return new Specification<Product>() {
//			@Override
//			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//			
//				List<Predicate> predicate = new ArrayList<>();
//				if (StringUtils.isNotBlank(productQueryDTO.getProduct_name())) {
//					predicate.add(criteriaBuilder.like(root.get("product_name").as(String.class),
//							"%" + productQueryDTO.getProduct_name() + "%"));
//				}
//				
//				Predicate[] pre = new Predicate[predicate.size()];
//				return query.where(predicate.toArray(pre)).getRestriction();
//			}
//		};
//	}
//}
