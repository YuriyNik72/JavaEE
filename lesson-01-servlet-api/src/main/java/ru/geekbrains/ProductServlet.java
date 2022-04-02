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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository)  getServletContext().getAttribute("productRepository");
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

        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            PrintWriter wr = resp.getWriter();
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>Id</th>");
            wr.println("<th>Title</th>");
            wr.println("<th>Cost</th>");
            wr.println("</tr>");


            for (Product product : productRepository.findAll()) {

                wr.println("<tr>");
                wr.println("<td><a href='" + "/servlet/product/" + product.getId() + "'>" + product.getId() + "</a></td>");
                wr.println("<td>" + product.getTitle() + "</td>");
                wr.println("<td>" + product.getCost() + "</td>");
                wr.println("</tr>");

            }

            wr.println("</table>");
        }else {
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id = Long.parseLong(matcher.group(1));
                Product product = this.productRepository.findById(id);
                if (product == null) {
                    resp.getWriter().println("<p>Product not found</p>");
                    resp.setStatus(404);
                    return;
                }
                resp.getWriter().println("<p>Id: " + product.getId() + "</p>");
                resp.getWriter().println("<p>Product: " + product.getTitle() + "</p>");
                resp.getWriter().println("<p>Cost: " + product.getCost() + "</p>");
            }else {
                resp.getWriter().println("<p>Bad parameters</p>");
                resp.setStatus(400);
            }
        }
    }
}
