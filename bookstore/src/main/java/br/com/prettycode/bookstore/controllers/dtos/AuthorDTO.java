package br.com.prettycode.bookstore.controllers.dtos;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AuthorDTO extends RepresentationModel<BookDTO> {

}
