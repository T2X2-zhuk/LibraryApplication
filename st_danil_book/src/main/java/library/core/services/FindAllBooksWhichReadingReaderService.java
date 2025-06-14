package library.core.services;

import library.core.database.jpa.JpaBookRepository;
import library.core.database.jpa.JpaReaderBookRepository;
import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Book;
import library.core.domain.Reader;
import library.core.requests.FindAllBooksWhichReadingReaderRequest;
import library.core.responses.FindAllBooksWhichReadingReaderResponse;
import library.core.validators.bookValidation.FindAllBooksWhichReadingReaderValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAllBooksWhichReadingReaderService {

    @Autowired private JpaReaderRepository readerRepository;

    @Autowired private JpaReaderBookRepository readerBookRepository;
    @Autowired private JpaBookRepository bookRepository;
    @Autowired private FindAllBooksWhichReadingReaderValidator validator;

    @Transactional
    public FindAllBooksWhichReadingReaderResponse execute(FindAllBooksWhichReadingReaderRequest request){
       List<ValidationError> coreErrorList = new ArrayList<>();
       coreErrorList.addAll(validator.validate(request));
       if (!coreErrorList.isEmpty()){
           return new FindAllBooksWhichReadingReaderResponse(coreErrorList,null);
       }else {
           List<Reader> readers = readerRepository.findByFirstNameAndLastName(request.getReaderFirstName(),request.getReaderLastName());
           Reader reader = readers.get(0);
           List<Book> books = new ArrayList<>();
           books.addAll(readerBookRepository.getAllBooksIdWhichReadingReaderById(reader));
           return new FindAllBooksWhichReadingReaderResponse(null,books);
       }
    }
}
