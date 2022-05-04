package ru.geekbrains.entity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {


//    @Query("select p " +
//            " from Product p " +
//            "where p.cost > ?1 ORDER BY ASC  or p.cost is null and " +
//            "      p.cost < ?2 ORDER BY ASC  or p.cost is null and " +
//            "       p.cost between ?1 and ?2")
//    List<Product> findProductByFilter(@Param("minCost") Long minCost,
//                                      @Param("maxCost") Long maxCost);


//    @Query("select p from Product p where p.cost > ?1")
//    Page<Product> findProductByCostAfter(Long minCost, Pageable pageable);
//
//
//    @Query("select p from Product p where p.cost < ?1")
//    Page<Product> findProductByCostBefore(Long maxCost, Pageable pageable);


    @Query("select p from Product p where p.cost between ?1 and ?2")
    Page<Product> findProductByCostBetween(Optional<Long> minCost, Optional<Long> maxCost, Pageable pageable);


}
