package com.shopping.repository;

import com.shopping.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, QuerydslPredicateExecutor<Product>,ProductRepositoryCustom {
    // 방법01 : Query 메소드 사용
    //application.properties 에서 spring.jpq.hibernate.ddl-auto=update


    //상품 이름으로 데이터를 조회합니다.
    List<Product> findProductByName(String name);


    // 특정 가격 보다 작은 상품들을 조회합니다.
    List<Product> findByPriceLessThan(Integer price);

    // 특정 가격 보다 작은 상품들을 조회하되 가격의 역순으로 조회합니다.
    List<Product> findByPriceLessThanOrderByPriceDesc(Integer price);


    // 방법 02 "@Query 어노테이션 사용
    // 주의) jpql 구문의 from에는 반드시 대소문자를 구분하여 entity 이름이 와야 합니다.
    @Query("select pr from Product pr where pr.description like "
            + "%:description% order by pr.price desc")
    List<Product> findByProductDetail01(@Param("description") String description);

    //'nativeQuery = ture' 옵션은 범용적이지 못하고 , 특정 데이터 베이스에 종속되는 구문 작성시 필요합니다.
    @Query(value = "select * from products pr where pr.description like "
            + "%:description% order by pr.price desc", nativeQuery = true)
    List<Product> findByProductDetail02(@Param("description") String description);

}