package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        productRepository.insert(new Product("Milk", 15));
        productRepository.insert(new Product("Bread", 10));
        productRepository.insert(new Product("Butter",150));
        productRepository.insert(new Product("Milk", 20));
        productRepository.insert(new Product("Bread", 23));
        productRepository.insert(new Product("Butter",170));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
