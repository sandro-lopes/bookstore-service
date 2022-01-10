package br.com.prettycode.bookstore.repositories;

import br.com.prettycode.bookstore.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}
