package br.com.prettycode.bookstore.repositories;

import br.com.prettycode.bookstore.domains.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
