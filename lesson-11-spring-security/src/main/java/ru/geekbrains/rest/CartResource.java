package ru.geekbrains.rest;



import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.LineItem;

import java.util.List;

@Data
@RequestMapping("/rest/v1/cart")
@RestController
public class CartResource {

    private final CartService cartService;

    @Autowired
    public CartResource(CartService cartService) {
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

    @GetMapping("/add")
    @ResponseBody
    public LineItem addProduct (@RequestParam("uesrId") long userId,
                                @RequestParam("productId") long productId,
                                @RequestParam(name = "count", required = false) Integer count){
        return cartService.addProductToUser(userId,productId,count==null ? 1:count);
    }


    @GetMapping("/remove/user/{uId}/product/{pId}")
    public void remove(@PathVariable("uId") long userId, @PathVariable("pId") long productId){
        cartService.removeProductFromUser(userId,productId);
    }
}
