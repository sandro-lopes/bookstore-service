package br.com.prettycode.bookstore.services;

import br.com.prettycode.bookstore.domains.Author;
import br.com.prettycode.bookstore.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public Page<Author> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }
}
