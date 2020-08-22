package com.shopped.api.controller;

import java.util.List;
import com.shopped.api.model.Cart;
import com.shopped.api.service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CartController
 */
@RequestMapping("/api/v1/cart")
@RestController
public class CartController {

    private CartService ts;

    @Autowired
    public CartController(CartService ts) {
        this.ts = ts;
    }

    @GetMapping(path = "active/{author}")
    public Cart getCurrent(@PathVariable("author") String author) {
        return ts.getCurrentByAuthor(author);
    }

    @PostMapping
    public Cart create(@RequestBody Cart cart) {
        return ts.create(cart);
    }

    @PutMapping
    public Cart update(@RequestBody Cart cart) {
        return ts.update(cart);
    }

    @DeleteMapping(path = "{id}/{author}")
    public Cart delete(@PathVariable("id") String id, @PathVariable("author") String author) {
        return ts.delete(new Cart(id, author));
    }

    @GetMapping(path = "{id}/{author}")
    public Cart get(@PathVariable("id") String id, @PathVariable("author") String author) {
        return ts.get(new Cart(id, author));
    }

    @GetMapping
    public List<Cart> getAll() {
        return ts.getAll();
    }

    @GetMapping(path = "{author}")
    public List<Cart> get(@PathVariable("author") String author) {
        return ts.getAllByAuthor(new Cart(author));
    }

}
