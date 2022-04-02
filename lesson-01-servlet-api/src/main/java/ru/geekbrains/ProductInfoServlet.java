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

//@WebServlet(urlPatterns = "/product/*")
//public class ProductInfoServlet extends HttpServlet {
//
//    private static Logger logger = LoggerFactory.getLogger(ProductInfoServlet.class);
//    private ProductRepository productRepository;
//    private Product product;
//
//    @Override
//    public void init() throws ServletException {
//        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        logger.debug("DoGet");
//
//
//       PrintWriter wr = resp.getWriter();
//        wr.println("<h2>Свойства продукта</h2>");
//
//        Long a = Long.valueOf(req.getPathInfo().substring(1));
//        wr.println("<p> Path Info=" + a + "</p>");
//
//
//        wr.println("<table>");
//        wr.println("<tr>");
//        wr.println("<th>Id</th>");
//        wr.println("<th>Title</th>");
//        wr.println("<th>Cost</th>");
//        wr.println("</tr>");
//
//        for(Product product : productRepository.findAll()) {
//             Long b = product.getId();
////             wr.printf("<p> Product ID= %d</p>%n", b);
//                 if(a.equals(b)) {
//                        wr.println("<tr>");
//                        wr.println("<td>" + product.getId() + "</td>");
//                        wr.println("<td>" + product.getTitle() + "</td>");
//                        wr.println("<td>" + product.getCost() + "</td>");
//                        wr.println("</tr>");
//                }
//        }
//
//
//        wr.println("</table>");
////        resp.getWriter().print("<p> Product" + productRepository.findById(a) + " </p>");
//    }
//}
