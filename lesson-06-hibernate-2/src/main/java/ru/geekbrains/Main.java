package ru.geekbrains;

import ru.geekbrains.model.Product;
import ru.geekbrains.DAO.ProductDAO;
import ru.geekbrains.DAO.ProductDAOImpl;
import ru.geekbrains.utils.SessionFactoryUtils;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();

        try {

           ProductDAO productDAO = new ProductDAOImpl(sessionFactoryUtils);

//            Product product = productDAO.findById(2l);
//            product.print();

//            productDAO.deleteById(2l);
           List<Product> product = productDAO.findAll();
            product.forEach(System.out::println);

//            Product product = productDAO.saveOrUpdate(new Product("Product2","solid",200l));
//            product.print();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sessionFactoryUtils.shutdown();
        }

    }
}
