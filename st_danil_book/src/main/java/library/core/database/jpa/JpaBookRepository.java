package library.core.database.jpa;

import library.core.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaBookRepository extends JpaRepository<Book, Long> {



    @Query(value = "SELECT b FROM Book b")
    List<Book> getAllBooks();

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByTitleAndAuthor(String title, String author);


}
