package it.epicode.U5W2D2.controllers;

import it.epicode.U5W2D2.entities.Blog;
import it.epicode.U5W2D2.payloads.NewBlogDTO;
import it.epicode.U5W2D2.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
    public Blog save(@RequestBody @Validated NewBlogDTO body, BindingResult validation) {
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

    @PostMapping("/upload")
    public String uploadExample(@RequestParam("avatar") MultipartFile body) throws IOException {
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return blogsService.uploadPicture(body);
    }
}
