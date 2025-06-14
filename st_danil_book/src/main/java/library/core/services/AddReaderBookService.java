package library.core.services;

import library.core.database.jpa.JpaBookRepository;
import library.core.database.jpa.JpaReaderBookRepository;
import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Book;
import library.core.domain.Reader;
import library.core.domain.ReaderBook;
import library.core.requests.AddReaderBookRequest;
import library.core.responses.AddReaderBookResponse;
import library.core.validators.readerBookValidation.AddReaderBookRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class AddReaderBookService {

    @Autowired private JpaReaderBookRepository readerBookRepository;
    @Autowired private JpaBookRepository bookRepository;
    @Autowired private AddReaderBookRequestValidator validator;
    @Autowired private JpaReaderRepository readerRepository;

    @Transactional
    public AddReaderBookResponse execute(AddReaderBookRequest request) {
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()){
            return new AddReaderBookResponse(errors);
        }
        List<Reader> readers = readerRepository.findByFirstNameAndLastName(request.getFirstNameReader(),request.getLastNameReader());
        Reader reader = readers.get(0);
        List<Book> bookList = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        Book book = bookList.get(0);
        ReaderBook readerBook = new ReaderBook(reader,book,request.getBook_out_date(),request.getBook_return_date());
        readerBookRepository.save(readerBook);

        return new AddReaderBookResponse(readerBook);
    }
}
