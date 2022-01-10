package br.com.prettycode.bookstore.controllers;

import br.com.prettycode.bookstore.controllers.assemblers.AuthorAssembler;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthorController {

    private final AuthorAssembler assembler;

}
