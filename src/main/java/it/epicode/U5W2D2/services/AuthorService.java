package it.epicode.U5W2D2.services;

import it.epicode.U5W2D2.entities.Author;
import it.epicode.U5W2D2.exceptions.NotFoundException;
import it.epicode.U5W2D2.repos.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Page<Author> getAuthors(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return authorRepository.findAll(pageable);
    }

    public Author findById(int id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Author save(Author body) {
        return authorRepository.save(body);
    }

    public Author findByIdAndUpdate(int id, Author body) {
        Author authorFound = this.findById(id);
        authorFound.setNome(body.getNome());
        authorFound.setCognome(body.getCognome());
        authorFound.setEmail(body.getEmail());
        return authorRepository.save(authorFound);
    }

    public void findByIdAndDelete(int id) {
        Author authorFound = this.findById(id);
        authorRepository.delete(authorFound);
    }
}
