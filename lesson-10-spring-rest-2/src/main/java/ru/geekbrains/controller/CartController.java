package ru.geekbrains.controller;



import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.LineItem;

import java.util.List;

@Data
@RequestMapping("/cart")
@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public List<LineItem> getProducts(@PathVariable("id") long id){
        return cartService.findProductsByUserId(id);
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public List<LineItem> getUsers(@PathVariable("id") long id){
        return cartService.findUsersByProductId(id);
    }

    @PostMapping("/add")
    @ResponseBody
    public LineItem addProduct (@RequestParam("uesrId") long userId,
                                @RequestParam("productId") long productId,
                                @RequestParam(name = "count", required = false) Integer count){
        return cartService.addProductToUser(userId,productId,count==null ? 1:count);
    }


    @DeleteMapping("/remove/user/{uId}/product/{pId}")
    public void remove(@PathVariable("uId") long userId, @PathVariable("pId") long productId){
        cartService.removeProductFromUser(userId,productId);
    }
}