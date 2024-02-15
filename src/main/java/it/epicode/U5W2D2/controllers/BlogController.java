package it.epicode.U5W2D2.controllers;

import it.epicode.U5W2D2.entities.Blog;
import it.epicode.U5W2D2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogsService;

    @GetMapping("")
    public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy) {
        return blogsService.getBlogs(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Blog findById(@PathVariable int id) {
        return blogsService.findById(id);
    }

    @PostMapping("")
    public Blog saveNewBook(@RequestBody Blog body) {
        return blogsService.save(body);
    }

    @PutMapping("/{id}")
    public Blog findByIdAndUpdate(@PathVariable int id, @RequestBody Blog body) {
        return blogsService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable int id) {
        blogsService.findByIdAndDelete(id);
    }
}
