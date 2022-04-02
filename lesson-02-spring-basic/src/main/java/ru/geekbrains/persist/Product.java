package ru.geekbrains.persist;

import java.util.Objects;

public class Product {

    private Long id;
    private String title;
    private int count;


 public Product(Long id,String title, int count) {
        this.id = id;
        this.title = title;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id,product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String
    toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + title + '\'' + ", count=" + count +
                '}';
    }
}
