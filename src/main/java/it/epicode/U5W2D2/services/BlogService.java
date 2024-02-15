package it.epicode.U5W2D2.services;

import it.epicode.U5W2D2.entities.Author;
import it.epicode.U5W2D2.entities.Blog;
import it.epicode.U5W2D2.exceptions.NotFoundException;
import it.epicode.U5W2D2.repos.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AuthorService authorService;

    public Page<Blog> getBlogs(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return blogRepository.findAll(pageable);
    }

    public Blog findById(int id) {
        return blogRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Blog save(Blog body) {
        Author author = authorService.findById(body.getAuthor().getId());
        body.setAuthor(author);
        return blogRepository.save(body);
    }

    public Blog findByIdAndUpdate(int id, Blog body) {
        Blog blog = this.findById(id);
        blog.setTitolo(body.getTitolo());
        blog.setContenuto(body.getContenuto());
        blog.setTempoDiLettura(body.getTempoDiLettura());
        return blogRepository.save(blog);

    }

    public void findByIdAndDelete(int id) {
        Blog blog = this.findById(id);
        blogRepository.delete(blog);
    }
}
