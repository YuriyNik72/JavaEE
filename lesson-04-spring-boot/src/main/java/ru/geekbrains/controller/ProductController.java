package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import javax.validation.Valid;


@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));;
        return "product_form";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        // TODO
        model.addAttribute("product",new Product("", "", 0l));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult binding) {
        if(binding.hasErrors()){
            return "product_form";
        }
        if(product.getCoast()<0 || product.getCoast()>=100000l){
          binding.rejectValue("coast","","Coast not value");
            return "product_form";
        }

    public String save(Product product) {
        productRepository.save(product);
        return "redirect:/product";
    }
      
    @GetMapping("/del/{id}")
    public String delete(@PathVariable long id){
       productRepository.delete(id);
       return "redirect:/product";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
