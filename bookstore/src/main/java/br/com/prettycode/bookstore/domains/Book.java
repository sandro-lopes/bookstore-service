package br.com.prettycode.bookstore.domains;

import br.com.prettycode.bookstore.domains.types.Category;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Book implements Serializable {

    @Id
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

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    public void authorship(Author author) {
        if (this.authors == null) {
            this.authors = new HashSet<>();
        }
        this.authors.add(author);
    }
}
