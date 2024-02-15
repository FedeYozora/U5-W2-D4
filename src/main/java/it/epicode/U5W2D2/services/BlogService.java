package it.epicode.U5W2D2.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.U5W2D2.entities.Blog;
import it.epicode.U5W2D2.exceptions.NotFoundException;
import it.epicode.U5W2D2.payloads.NewBlogDTO;
import it.epicode.U5W2D2.repos.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private Cloudinary cloudinary;

    public Page<Blog> getBlogs(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return blogRepository.findAll(pageable);
    }

    public Blog findById(int id) {
        return blogRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Blog save(NewBlogDTO body) {
        Blog blog = new Blog();
        blog.setCategoria(body.categoria());
        blog.setTitolo(body.titolo());
        blog.setCover(body.cover());
        blog.setContenuto(body.contenuto());
        blog.setTempoDiLettura(body.tempoDiLettura());
        blog.setAvatar(body.avatar());
        return blogRepository.save(blog);
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

    public String uploadPicture(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }
}
