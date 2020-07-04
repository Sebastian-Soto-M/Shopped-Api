package com.shopped.api.controller;

import java.util.List;

import com.shopped.api.model.Recipe;
import com.shopped.api.service.RecipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * RecipeController
 */
@RequestMapping("/api/v1/recipe")
@RestController
public class RecipeController {

    private RecipeService ts;

    @Autowired
    public RecipeController(RecipeService ts) {
        this.ts = ts;
    }

    @PostMapping
    public Recipe create(@RequestBody Recipe recipe) {
        return ts.create(recipe);
    }

    @PutMapping
    public Recipe update(@RequestBody Recipe recipe) {
        return ts.update(recipe);
    }

    @DeleteMapping(path = "{id}")
    public Recipe delete(@PathVariable("id") String id) {
        return ts.delete(new Recipe(id));
    }

    @GetMapping(path = "{id}")
    public Recipe get(@PathVariable("id") String id) {
        return ts.get(new Recipe(id));
    }

    @GetMapping
    public List<Recipe> getAll() {
        return ts.getAll();
    }

}
