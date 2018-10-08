package com.ierp.product.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.vendor.domain.Vendor;


public class ProductQueryDTO {

	private List<Vendor> vendor;
	private String productName;
	private String productPrice;
	private String productDesc;
	private String productPic;	  //图片路径
	private String productSpec;  //规格
	private String productUuid;  //唯一识别号
	private String productStatus;//状态  1.存货  2.缺货  3.下架
	private Integer productStock; //库存
	

	public List<Vendor> getVendor() {
		return vendor;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public String getProductPic() {
		return productPic;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public String getProductUuid() {
		return productUuid;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public Integer getProductStock() {
		return productStock;
	}


	


	public void setVendor(List<Vendor> vendor) {
		this.vendor = vendor;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public void setProductUuid(String productUuid) {
		this.productUuid = productUuid;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	@SuppressWarnings({ "serial"})
	public static Specification<Product> getWhereClause(final ProductQueryDTO productQueryDTO) {
		return new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
			
				List<Predicate> predicate = new ArrayList<>();
				if (StringUtils.isNotBlank(productQueryDTO.getProductName())) {
					predicate.add(criteriaBuilder.like(root.get("product_name").as(String.class),
							"%" + productQueryDTO.getProductName() + "%"));
				}
				if (StringUtils.isNotBlank(productQueryDTO.getProductUuid())) {
					predicate.add(criteriaBuilder.like(root.get("product_uuid").as(String.class),
							"%" + productQueryDTO.getProductUuid() + "%"));
				}
				
				Predicate[] pre = new Predicate[predicate.size()];
				return query.where(predicate.toArray(pre)).getRestriction();
			}
		};
	}
}
