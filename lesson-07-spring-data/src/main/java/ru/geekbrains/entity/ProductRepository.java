package ru.geekbrains.entity;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;


    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class)
                .getResultList();
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }
    @Transactional
    public Product save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }else {
            em.merge(product);
        }
        return product;
    }
    @Transactional
    public void delete(long id) {
       em.createQuery("delete from Product  where id = :id")
               .setParameter("id", id)
               .executeUpdate();
    }

}
