package ru.geekbrains.service;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.dto.UserDto;

@Data
@NoArgsConstructor
public class LineItem {

    private ProductDto productDto;
    private UserDto userDto;
    private int productCount;

    public LineItem(ProductDto productDto, UserDto userDto, int productCount) {
        this.productDto = productDto;
        this.userDto = userDto;
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "productDto=" + productDto +
                ", userDto=" + userDto +
                ", productCount=" + productCount +
                '}';
    }
}
