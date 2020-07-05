package com.shopped.api.controller;

import java.util.List;

import com.shopped.api.model.Bundle;
import com.shopped.api.service.BundleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * BundleController
 */
@RequestMapping("/api/v1/bundle")
@RestController
public class BundleController {

    private BundleService ts;

    @Autowired
    public BundleController(BundleService ts) {
        this.ts = ts;
    }

    @PostMapping
    public Bundle create(@RequestBody Bundle bundle) {
        return ts.create(bundle);
    }

    @PutMapping
    public Bundle update(@RequestBody Bundle bundle) {
        return ts.update(bundle);
    }

    @DeleteMapping(path = "{id}/{author}")
    public Bundle delete(@PathVariable("id") String id, @PathVariable("author") String author) {
        return ts.delete(new Bundle(id, author));
    }

    @GetMapping(path = "{id}/{author}")
    public Bundle get(@PathVariable("id") String id, @PathVariable("author") String author) {
        return ts.get(new Bundle(id, author));
    }

    @GetMapping
    public List<Bundle> getAll() {
        return ts.getAll();
    }

}
