package library.core.database.jpa;

import library.core.domain.Book;
import library.core.domain.Reader;
import library.core.domain.ReaderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaReaderBookRepository extends JpaRepository<ReaderBook, Long> {


    Optional<ReaderBook> findById(Long id);

    @Query(value = "SELECT rb FROM ReaderBook rb WHERE reader = :reader AND book= :book")
    List<ReaderBook> findByReaderIdAndBookId(@Param("reader") Reader reader, @Param("book") Book book);

    @Query(value = "SELECT rb FROM ReaderBook rb")
    List<ReaderBook> getAllReaderBooksSimple();

    @Query(value = "SELECT book FROM ReaderBook WHERE reader = :reader")
    List<Book> getAllBooksIdWhichReadingReaderById(@Param("reader") Reader reader);
}
