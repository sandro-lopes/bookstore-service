package br.com.prettycode.bookstore.services;

import br.com.prettycode.bookstore.domains.Author;
import br.com.prettycode.bookstore.domains.Book;
import br.com.prettycode.bookstore.repositories.BookRepository;
import br.com.prettycode.bookstore.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    private final AuthorService authorService;

    public Page<Book> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Book> findById(String isbn) {
        return repository.findById(isbn);
    }

    public Book create(Book book) {
        return repository.saveAndFlush(book);
    }

    public Book update(String isbn, Book book) {
        //check conflict name
        book.setIsbn(isbn);
        return repository.saveAndFlush(book);
    }

    public void delete(String isbn) {
        repository.deleteById(isbn);
    }

    public Author authorship(String isbn, Long authorId) {
        //FIXME Resource esta muito relacionado a camada de controle. Mudar excecao e colocal mensagem explicativa.
        Book book = repository.findById(isbn).orElseThrow(ResourceNotFoundException::new);
        Author author = authorService.findById(authorId).orElseThrow(ResourceNotFoundException::new);
        book.authorship(author);
        repository.save(book);
        return author;
    }
}
