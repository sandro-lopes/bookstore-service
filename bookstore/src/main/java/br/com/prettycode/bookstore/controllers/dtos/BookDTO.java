package br.com.prettycode.bookstore.controllers.dtos;

import br.com.prettycode.bookstore.domains.types.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class BookDTO extends RepresentationModel<BookDTO> {

    private String isbn;

    @NotBlank
    @Size(min = 1, max = 80)
    private String title;

    @NotBlank
    @Size(min = 1, max = 80)
    private String publisher;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String summary;

    private LocalDate releasedIn;

    private Integer numberOfPages;

    private Set<Long> authorsId;
}
