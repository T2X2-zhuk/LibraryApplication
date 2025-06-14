package library.core.database.orm;


import library.core.domain.Book;
import library.core.domain.Reader;
import library.core.domain.ReaderBook;

import java.util.List;

public interface ReaderBookRepository {

    void save(ReaderBook readerBook);

    void delete(ReaderBook readerBook);

    List<ReaderBook> getByIdReaderAndIdBook(Book book, Reader reader);

    List<ReaderBook> getAllReaderBooks(Reader reader);

    List<ReaderBook> getAllReaderBooksSimple();

    List<Book> getAllBooksId(Reader reader);
}
