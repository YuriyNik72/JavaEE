package ru.geekbrains;

import org.hibernate.Session;
import ru.geekbrains.DAO.UserDAO;
import ru.geekbrains.DAO.UserDAOImpl;
import ru.geekbrains.model.Product;
import ru.geekbrains.DAO.ProductDAO;
import ru.geekbrains.DAO.ProductDAOImpl;
import ru.geekbrains.model.User;
import ru.geekbrains.utils.SessionFactoryUtils;

import java.util.List;

public class MainHibernate2 {

    public static void main(String[] args) {

        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        Session session = null;
        try {

           ProductDAO productDAO = new ProductDAOImpl(sessionFactoryUtils);
            UserDAO userDAO = new UserDAOImpl(sessionFactoryUtils);


//           User user = userDAO.findById(1l);
//            System.out.println(user);
//           List<Product> p = user.getProducts();
//            p.forEach(System.out::println);

            Product product = productDAO.findById(1l);
            System.out.println(product);
            List<User> u = product.getUsers();
            u.forEach(System.out::println);

//            Product product = productDAO.findById(2l);
//            product.print();


//            Product product = productDAO.saveOrUpdate(new Product("Product2","solid",200l));
//            product.print();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sessionFactoryUtils.shutdown();
        }

    }
}
