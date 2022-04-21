package ru.geekbrains;

import ru.geekbrains.model.SessionFactoryUtils;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.ProductDAO;
import ru.geekbrains.model.ProductDAOImpl;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();

        try {

           ProductDAO productDAO = new ProductDAOImpl(sessionFactoryUtils);

            Product product = productDAO.findById(2l);
            product.print();

//           List<Product> product = productDAO.findAll();
//            product.forEach(System.out::println);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sessionFactoryUtils.shutdown();
        }

    }
}
