package ru.geekbrains.service;


import org.springframework.data.domain.Page;
import ru.geekbrains.dto.ProductDto;

import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findProductByFilter(Optional<Long> minCost,
                                         Optional<Long> maxCost,
                                         Integer page,
                                         Integer size,
                                         String sortField);

    Optional<ProductDto> findById(long id);

    ProductDto save (ProductDto product);

    void deleteById(long id);
}
