package ru.geekbrains.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "cost",nullable = false)
    private Long cost;

    public Product(Long id, String title, String description, Long cost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
    }

}
