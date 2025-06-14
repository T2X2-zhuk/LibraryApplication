package library.core.services;
import library.core.database.jpa.JpaBookRepository;
import library.core.database.jpa.JpaReaderBookRepository;
import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Book;
import library.core.domain.Reader;
import library.core.domain.ReaderBook;
import library.core.requests.DeleteReaderBookRequest;
import library.core.responses.DeleteReaderBookResponse;
import library.core.validators.readerBookValidation.DeleteReaderBookRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DeleteReaderBookService {

    @Autowired private JpaBookRepository bookRepository;
    @Autowired private JpaReaderRepository readerRepository;
    @Autowired private JpaReaderBookRepository readerBookRepository;
    @Autowired private DeleteReaderBookRequestValidator validator;

    @Transactional
    public DeleteReaderBookResponse execute(DeleteReaderBookRequest request){
        List<ValidationError> errors = validator.validate(request);

        if (!errors.isEmpty()){
            return new DeleteReaderBookResponse(errors);
        }else {
            deleteReaderBookFromDataBase(request);
            return new DeleteReaderBookResponse(true);
        }
    }

    private void deleteReaderBookFromDataBase(DeleteReaderBookRequest request) {
        List<Reader> reader = readerRepository.findByFirstNameAndLastName(request.getFirstNameReader(), request.getLastNameReader());
        List<Book>  books = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());
        List<ReaderBook> readerBook = readerBookRepository.findByReaderIdAndBookId(reader.get(0),books.get(0));
        readerBookRepository.delete(readerBook.get(0));
    }
}
