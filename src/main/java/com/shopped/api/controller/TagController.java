package com.shopped.api.controller;

import java.util.List;

import com.shopped.api.model.Tag;
import com.shopped.api.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TagController
 */
@RequestMapping("/api/v1/tag")
@RestController
public class TagController {

    private TagService ts;

    @Autowired
    public TagController(TagService ts) {
        this.ts = ts;
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return ts.create(tag);
    }

    @PutMapping
    public Tag update(@RequestBody Tag tag) {
        return ts.update(tag);
    }

    @DeleteMapping(path = "{name}/{type}")
    public Tag delete(@PathVariable("name") String name, @PathVariable("type") String type) {
        return ts.delete(new Tag(name, type));
    }

    @GetMapping(path = "{name}/{type}")
    public Tag get(@PathVariable("name") String name, @PathVariable("type") String type) {
        return ts.get(new Tag(name, type));
    }

    @GetMapping
    public List<Tag> getAll() {
        return ts.getAll();
    }

}
