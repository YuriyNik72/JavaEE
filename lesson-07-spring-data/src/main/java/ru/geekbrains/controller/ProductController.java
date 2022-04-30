package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Product;
import ru.geekbrains.entity.ProductRepository;

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
    public String listPage(@RequestParam(name = "mincost", required=false, defaultValue = "1") Long min,
                           @RequestParam(name = "maxcost", required=false, defaultValue= "1000000000") Long max,
                           Model model) {
            model.addAttribute("products", productRepository.findAllProductByCostBetween(min, max));
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
        model.addAttribute("product",new Product("", "", 0l));
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult binding) {
        if(binding.hasErrors()){
            return "product_form";
        }
        if(product.getCost()<=0|| product.getCost()>=100000){
          binding.rejectValue("cost","","Cost not value");
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }
    @GetMapping("/del/{id}")
    public String delete(@PathVariable long id){
//       productRepository.delete(id);
       productRepository.deleteById(id);
       return "redirect:/product";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
