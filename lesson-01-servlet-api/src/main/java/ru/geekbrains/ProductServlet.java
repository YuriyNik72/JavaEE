package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Milk", 15));
        this.productRepository.insert(new Product("Bread", 10));
        this.productRepository.insert(new Product("Butter",150));
        this.productRepository.insert(new Product("Milk", 20));
        this.productRepository.insert(new Product("Bread", 23));
        this.productRepository.insert(new Product("Butter",170));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().println("<h1>Привет от сервлета!!!</h1>");
//        resp.getWriter().println("<p>contextPath: " + req.getContextPath() + "</p>");
//        resp.getWriter().println("<p>servletPath: " + req.getServletPath() + "</p>");
//        resp.getWriter().println("<p>pathInfo: " + req.getPathInfo() + "</p>");
//        resp.getWriter().println("<p>queryString: " + req.getQueryString() + "</p>");
//        resp.getWriter().println("<p>param1: " + req.getParameter("param1") + "</p>");
//        resp.getWriter().println("<p>param2: " + req.getParameter("param2") + "</p>");
            logger.debug("Print");

        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>Id</th>");
        wr.println("<th>Title</th>");
        wr.println("<th>Cost</th>");
        wr.println("</tr>");


        for(Product product : productRepository.findAll()) {

                wr.println("<tr>");
                wr.println("<td><a href='" + "/servlet/product/" + product.getId() +"'>" + product.getId() +"</a></td>");
                wr.println("<td>" + product.getTitle() + "</td>");
                wr.println("<td>" + product.getCost() + "</td>");
                wr.println("</tr>");

        }

        wr.println("</table>");
    }
}
