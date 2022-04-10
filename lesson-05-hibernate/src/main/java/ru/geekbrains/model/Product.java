package ru.geekbrains.model;

import javax.persistence.*;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Long coast;

    @Column
    private String description;

    public Product() {
    }

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

    @Override
    public String toString(){
        return "Product{" +
                "id=" + id +
                ", product='" + title + '\'' +
                ", description='" + description + '\'' +
                ", coast='" + coast + '\'' +
                '}';
    }

}
