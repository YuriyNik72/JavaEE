package ru.geekbrains.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


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

    public ProductDto() {
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
