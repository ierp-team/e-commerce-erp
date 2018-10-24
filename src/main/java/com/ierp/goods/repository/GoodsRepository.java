package com.ierp.goods.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.ierp.goods.domain.Goods;
import com.ierp.vendormodule.vendor.domain.Vendor;


/**
 * PagingAndSortingRepository：分页和排序
 * JpaSpecificationExecutor：自定义高级（动态条件组装）查询
 *
 */
@Repository
public interface GoodsRepository extends PagingAndSortingRepository<Goods, Long>
										, JpaSpecificationExecutor<Goods> {

	@Query(value="SELECT sum(goods_stock) FROM t_goods WHERE goods_uuid=?1", nativeQuery=true)
    int sumGoodsStock(String uuid);
	
	@Query
    Goods findByGoodsUuidAndVendor(String goodsUuid, Vendor vendor);

	@Query
	List<Goods> findByGoodsUuid(String uuid);
	
}
