package ru.geekbrains.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.dto.ExceptionDto;
import ru.geekbrains.dto.ProductDto;
import ru.geekbrains.service.ProductService;
import java.sql.SQLException;
import java.util.Optional;

@RequestMapping("/rest/v1/product")
@RestController
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    private Page<ProductDto> findAll(@RequestParam(name = "mincost", required=false) Optional<Long> minCost,
                                     @RequestParam (name = "maxcost", required=false) Optional<Long> maxCost,
                                     @RequestParam Optional<Integer> page,
                                     @RequestParam Optional<Integer> size,
                                     @RequestParam Optional<String> sortField) {

        Integer pageValue = page.orElse(1)-1;
        Integer sizeValue = size.orElse(5); // сколько строк будет выводится
        String sortFieldValue = sortField
                .filter(s -> !s.isBlank())
                .orElse("id");

        return productService.findProductByFilter(
                minCost,
                maxCost,
                pageValue,
                sizeValue,
                sortFieldValue);

    }

    @GetMapping("/{id}/id")
    public ProductDto findById(@PathVariable long id){
        return productService.findById(id)
                .orElseThrow(()-> new NotFoundException("Product not found"));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto product){
        if(product.getId() !=null){
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        return productService.save(product);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto product){
        if(product.getId() ==null){
            throw new IllegalArgumentException("Created product should have id");
        }
        return productService.save(product);
    }

    @DeleteMapping("/{id}/id")
    public int delete(@PathVariable long id){
        productService.deleteById(id);
       return HttpStatus.OK.value();
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ExceptionDto exception(SQLException ex) {
        return new ExceptionDto();
    }

//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler
//    public String notFoundException(NotFoundException ex) {
//        return ex.getMessage();
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler
//    public String illegalArgumentException(IllegalArgumentException ex) {
//        return ex.getMessage();
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler
//    public String sqlException(SQLException ex) {
//        return ex.getMessage();
//    }

}
