package com.shopped.api.controller;

import java.util.List;

import com.shopped.api.model.Cart;
import com.shopped.api.model.Recipe;
import com.shopped.api.service.CartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CartController
 */
@RequestMapping("/api/v1/cart")
@RestController
public class CartController {

    private CartService ts;

    private Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    public CartController(CartService ts) {
        this.ts = ts;
    }

    @GetMapping(path = "active/{author}")
    public Cart getCurrent(@PathVariable("author") String author) {
        return ts.getCurrentByAuthor(author);
    }

    @PostMapping(path = "replace/{author}")
    public Cart replaceCart(@PathVariable("author") String author) {
        return ts.replaceCart(author);
    }

    @GetMapping(path = "{author}")
    public List<Cart> getAllByAuthor(@PathVariable("author") String author) {
        return ts.getAllByAuthor(author);
    }

    @PutMapping
    public Cart update(Cart c) {
        try {
            logger.warn(c.toString());
        } catch (NullPointerException e) {
            logger.error("Cart is null");
        }
        return ts.update(c);
    }

    @PostMapping(path = "merge")
    public Cart mergeRecipe(Recipe r) {
        return ts.mergeRecipe(r);
    }

}
