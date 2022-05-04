package ru.geekbrains.entity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


    @Query("select p from Product p where p.cost between ?1 and ?2")
    Page<Product> findProductByCostBetween(Optional<Long> minCost, Optional<Long> maxCost, Pageable pageable);


}
