package ru.geekbrains.DAO;

import ru.geekbrains.model.Product;

import java.util.List;

public interface ProductDAO {
    Product findById(Long id);
    List<Product> findAll();
    Product saveOrUpdate(Product product);
    void deleteById(Long id);
}
