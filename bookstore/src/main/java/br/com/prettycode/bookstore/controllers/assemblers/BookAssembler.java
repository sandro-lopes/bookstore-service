package br.com.prettycode.bookstore.controllers.assemblers;

import br.com.prettycode.bookstore.controllers.BookController;
import br.com.prettycode.bookstore.controllers.dtos.BookDTO;
import br.com.prettycode.bookstore.domains.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Configuration
public class BookAssembler extends RepresentationModelAssemblerSupport<Book, BookDTO> {

    @Autowired
    private ModelMapper mapper;

    public BookAssembler() {
        super(BookController.class, BookDTO.class);
    }

    @Override
    public BookDTO toModel(Book book) {
        return mapper.map(book, BookDTO.class)
                .add(linkTo(methodOn(BookController.class).find(book.getIsbn())).withSelfRel())
                .add(linkTo(methodOn(BookController.class).authorship("1", 1L)).withRel("Alejandro"));
    }

    public Book toEntity(BookDTO bookDTO) {
        return mapper.map(bookDTO, Book.class);
    }

    public Page<BookDTO> toModelPage(Page<Book> page) {
        return page.map(this::toModel);
    }
}
