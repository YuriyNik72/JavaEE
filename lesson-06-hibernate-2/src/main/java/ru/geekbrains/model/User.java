package ru.geekbrains.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "Id")
    private Long id;

    @Column(name = "Name",nullable = false)
    private String name;


    @ManyToMany
    @JoinTable(
            name = "users_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    public User() {
    }

    public User(String name) {
        this.name = name;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts(){
        return products;
    }
    public void setProducts(List<Product> products){
        this.products = products;
    }


    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", user='" + name +"'}";
    }

    public void print() {
        System.out.println("User id= " + id +" ; User name= " + name);
    }
}
