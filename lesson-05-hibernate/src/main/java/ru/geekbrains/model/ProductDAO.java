package ru.geekbrains.model;

import java.util.List;

public interface ProductDAO  {
    Product findById(Long id);
    List<Product> findAll();
    Product saveOrUpdate(Product product);
    void deleteById(Long id);
}
