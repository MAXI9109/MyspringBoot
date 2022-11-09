package com.shopping.repository;

import com.shopping.entity.Saram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface SaramRepository extends JpaRepository<Saram, String>, QuerydslPredicateExecutor {
//
//    List<Saram> ByOrderByNameAsc(String name);
//
//    List<Saram> findByAddressEquals(String address);
//
//    List<Saram> findByOrderBySalaryDesc();
//
//    @Query("select pr from SaramProduct pr where pr.description like"
//            + "%:description% order by pr.price desc")
//    List<Saram> findBySaramRepositoryTest02(@Param("description") String description);





}

