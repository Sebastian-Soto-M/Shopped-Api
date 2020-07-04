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

    @DeleteMapping(path = "{id}/{type}")
    public Bundle delete(@PathVariable("id") String id, @PathVariable("type") String type) {
        return ts.delete(new Bundle(id, type));
    }

    @GetMapping(path = "{name}/{type}")
    public Bundle get(@PathVariable("name") String name, @PathVariable("type") String type) {
        return ts.get(new Bundle(name, type));
    }

    @GetMapping
    public List<Bundle> getAll() {
        return ts.getAll();
    }

}
