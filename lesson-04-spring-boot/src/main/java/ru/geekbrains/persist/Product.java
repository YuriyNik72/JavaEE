package ru.geekbrains.persist;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


public class Product {

    private Long id;

    private String title;

    @Min(0l)
    @Max(100000l)
    private Long coast;

    private String description;

    public Product(String title, String description, Long coast ) {
        this.title = title;
        this.description = description;
        this.coast = coast;
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

    public Long getCoast() {
        return coast;
    }

    public void setCoast(Long coast) {
        this.coast = coast;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
