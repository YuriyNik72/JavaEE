package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.service.ProductService;
import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<Long> minCostFilter,
                           @RequestParam Optional<Long> maxCostFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam Optional<String> sortField,
                           Model model) {
        Long minCostFilterValue = minCostFilter
                .filter(s -> !s.toString().isBlank())
                .orElse(null);
        Long maxCostFilterValue = maxCostFilter
                .filter(s -> !s.toString().isBlank())
                .orElse(null);
        Integer pageValue = page.orElse(1)-1;
        Integer sizeValue = size.orElse(5); // сколько строк будет выводится
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");

          model.addAttribute("products", productService.findProductByFilter(
                  minCostFilterValue,
                  maxCostFilterValue,
                  pageValue,
                  sizeValue,
                  sortFieldValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));;
        return "product_form";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product",new ProductDto());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("product") ProductDto product, BindingResult binding) {
        if(binding.hasErrors()){
            return "product_form";
        }
        if(product.getCost()<=0|| product.getCost()>=100000000){
          binding.rejectValue("cost","","Cost not value");
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        productService.deleteById(id);
       return "redirect:/product";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundExceptionHandler(Model model, NotFoundException ex) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}
