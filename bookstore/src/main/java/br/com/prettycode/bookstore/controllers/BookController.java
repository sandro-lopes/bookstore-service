package br.com.prettycode.bookstore.controllers;

import br.com.prettycode.bookstore.controllers.assemblers.AuthorAssembler;
import br.com.prettycode.bookstore.controllers.dtos.AuthorDTO;
import br.com.prettycode.bookstore.controllers.dtos.BookDTO;
import br.com.prettycode.bookstore.domains.Author;
import br.com.prettycode.bookstore.domains.Book;
import br.com.prettycode.bookstore.services.BookService;
import br.com.prettycode.bookstore.controllers.assemblers.BookAssembler;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookAssembler bookAssembler;
    private final AuthorAssembler authorAssembler;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookAssembler.toEntity(bookDTO);
        Book newBook = bookService.create(book);
        BookDTO newBookDTO = bookAssembler.toModel(newBook);

        URI uri = newBookDTO.getLink(IanaLinkRelations.SELF).map(Link::toUri).orElse(null);

        return ResponseEntity
                .created(uri)
                .body(newBookDTO);
    }

    @GetMapping
    public ResponseEntity<Page<BookDTO>> findAll(Pageable pageable) {
        Page<Book> page = bookService.findAll(pageable);

        return ResponseEntity
                .ok(bookAssembler.toModelPage(page));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookDTO> find(@PathVariable("isbn") String isbn) {
        Book book = bookService.findById(isbn).orElseThrow(RuntimeException::new);

        return ResponseEntity
                .ok(bookAssembler.toModel(book));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookDTO> update(@PathVariable("isbn") String isbn, @Valid @RequestBody BookDTO bookDTO) {
        Book book = bookAssembler.toEntity(bookDTO);
        Book updatedBook = bookService.update(isbn, book);

        return ResponseEntity
                .ok(bookAssembler.toModel(updatedBook));
    }

    @PutMapping("/{isbn}/authors/{authorId}")
    public ResponseEntity<AuthorDTO> authorship(@PathVariable("isbn") String isbn, @PathVariable("authorId") Long authorId) {
        Author author = bookService.authorship(isbn, authorId);
        AuthorDTO authorDTO = authorAssembler.toModel(author);

        return ResponseEntity
                .ok(authorDTO);
    }

}
