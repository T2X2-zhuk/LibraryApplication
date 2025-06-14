package library.core.database.orm;

import library.core.domain.Book;

import java.util.List;

public interface BookRepository {

	void save(Book book);

	boolean deleteById(Long id);

	List<Book> getAllBooks();

	List<Book> findByTitle(String title);

	List<Book> findByAuthor(String author);

	List<Book> findByTitleAndAuthor(String title, String author);

	boolean isPresentBookInDataBase(Book book);
}
