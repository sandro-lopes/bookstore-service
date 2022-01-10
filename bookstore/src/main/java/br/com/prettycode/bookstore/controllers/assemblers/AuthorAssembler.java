package br.com.prettycode.bookstore.controllers.assemblers;

import br.com.prettycode.bookstore.controllers.AuthorController;
import br.com.prettycode.bookstore.controllers.dtos.AuthorDTO;
import br.com.prettycode.bookstore.domains.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

@Configuration
public class AuthorAssembler extends RepresentationModelAssemblerSupport<Author, AuthorDTO> {

    @Autowired
    private ModelMapper mapper;

    public AuthorAssembler() {
        super(AuthorController.class, AuthorDTO.class);
    }

    @Override
    public AuthorDTO toModel(Author author) {
        return mapper.map(author, AuthorDTO.class);
//                .add(linkTo(methodOn(AuthorController.class).find(author.getId())).withSelfRel())
//                FIXME Correct the method t search after crating it.
//                .add(linkTo(methodOn(AuthorController.class).find(null)).withRel("authors"));
    }
}
