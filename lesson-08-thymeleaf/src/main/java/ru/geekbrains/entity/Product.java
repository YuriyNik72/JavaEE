package ru.geekbrains.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Min(1)
    @Max(100000)
    @Column(nullable = false)
    private Long cost;

       public Product(String title, String description, Long cost ) {
        this.title = title;
        this.description = description;
        this.cost = cost;
    }

    public Product() {

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
