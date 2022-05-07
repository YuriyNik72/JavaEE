package ru.geekbrains.service;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.dto.UserDto;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repository.ProductRepository;
import ru.geekbrains.entity.User;
import ru.geekbrains.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Data
public class CartService {

    private final ProductRepository productRepository;

    private final List<LineItem> lineItems;

    private final UserRepository userRepository;

    @Autowired
    public CartService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.lineItems = new ArrayList<>();
    }



    public List<LineItem> findUsersByProductId(long productId) {
        return lineItems.stream().filter(item -> Objects.equals(item.getProductDto().getId(), productId)).collect(Collectors.toList());
    }

    public List<LineItem> findProductsByUserId(long userId) {
        return lineItems.stream().filter(item -> Objects.equals(item.getUserDto().getId(), userId)).collect(Collectors.toList());
    }

    public LineItem addProductToUser(long userId, long productId, int count) {
        if(!lineItems.isEmpty()){
            List<LineItem> list = findProductsByUserId(userId).stream().filter(item -> item.getProductDto().getId().equals(productId)).collect(Collectors.toList());
            if(!list.isEmpty()){
                list.stream().findFirst().ifPresent(item -> item.setProductCount(item.getProductCount() + count));
                return list.get(0);
            }
        }
        LineItem result = new LineItem(productMapper(productRepository.getById(productId)), userMapper(userRepository.getById(userId)), count);
        lineItems.add(result);
        return result;
    }

    private ProductDto productMapper(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getDescription(), product.getCost());
    }

    private UserDto userMapper(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
    }

    public void removeProductFromUser(long userId, long productId) {
        if(!lineItems.isEmpty()){
            List<LineItem> list = findProductsByUserId(userId).stream().filter(item -> item.getProductDto().getId().equals(productId)).collect(Collectors.toList());
            if(!list.isEmpty()){
                lineItems.remove(list.get(0));
            }
        }
    }
}
