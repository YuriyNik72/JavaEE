package ru.geekbrains.controller;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.entity.Product;

public final class ProductSpecifications {

    public static Specification<Product> minCostContaining(Long minCost){
        return (root, query, criteriaBuilder)-> criteriaBuilder.like(root.get("mincost"), "%" + minCost + "%");
    }

    public static Specification<Product> maxCostContaining(Long maxCost){
        return (root, query, criteriaBuilder)-> criteriaBuilder.like(root.get("maxcost"), "%" + maxCost + "%");
    }
}
