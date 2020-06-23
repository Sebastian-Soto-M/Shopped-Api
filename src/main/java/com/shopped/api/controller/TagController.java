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
    public String createTag(@RequestBody Tag tag) {
        ts.createTag(tag);
        return tag.getName();
    }
    
    @DeleteMapping
    public boolean deleteTag(@RequestBody Tag tag) {
    	return ts.deleteTag(tag);
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return ts.getAllTags();
    }

    @GetMapping(path = "{name}")
    public Tag getTag(@PathVariable("name") String name) {
        Tag t = ts.getTagByName(name).orElse(null);
        if (t != null) {
            return t;
        }
        return new Tag("Not Found", "Not Found", "Not Found", "Not Found");
    }

}
