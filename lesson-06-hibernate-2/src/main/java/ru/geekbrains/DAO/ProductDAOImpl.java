package ru.geekbrains.DAO;


import org.hibernate.Session;
import ru.geekbrains.DAO.ProductDAO;
import ru.geekbrains.model.Product;
import ru.geekbrains.utils.SessionFactoryUtils;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDAOImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }



    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }
    @Override
    public List<Product> findAll () {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> product = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public Product saveOrUpdate (Product product) {
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
        return product;
    }

    @Override
    public void deleteById (Long id){
        try (Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.createQuery("delete from Product p where p.id = :id")
                    .setParameter("id",id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

}
