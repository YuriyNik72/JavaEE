package ru.geekbrains.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class ProductDto {

        private Long id;

        @NotBlank
        private String title;

        @NotBlank
        private String description;

        @Min(1)
        @Max(100000)
        private Long cost;

    public ProductDto(Long id, String title, String description, Long cost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
    }

}
