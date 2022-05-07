package ru.geekbrains.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findProductByFilter(Optional<Long> minCost,
                                                Optional<Long> maxCost,
                                                Integer page,
                                                Integer size,
                                                String sortField) {
        Page<Product> choice;
        if(!minCost.isPresent() && minCost.isEmpty() && !maxCost.isPresent() && maxCost.isEmpty()){
            choice= productRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)));
        }else {
//            if (minCost != null && maxCost == null) {
//                choice= productRepository.findProductByCostBefore(minCost,  PageRequest.of(page, size, Sort.by(sortField)));
//            }else if(maxCost != null && minCost ==null) {
//                choice= productRepository.findProductByCostAfter(maxCost, PageRequest.of(page, size, Sort.by(sortField)));
//            }else {
                choice= productRepository.findProductByCostBetween(minCost, maxCost, PageRequest.of(page, size, Sort.by(sortField)));
//            }
        }
        return choice.map(ProductServiceImpl::productToDto);
    }

    @Override
    public Optional<ProductDto> findById(long id) {
        return productRepository.findById(id).map(ProductServiceImpl::productToDto);
    }

    @Override
    public ProductDto save(ProductDto product) {
        return productToDto(
                productRepository.save(
                        new Product(
                                product.getId(),
                                product.getTitle(),
                                product.getDescription(),
                                product.getCost())
                )
        );
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    private static ProductDto productToDto(Product product){
      return new ProductDto(product.getId(), product.getTitle(), product.getDescription(), product.getCost());
    }
}
