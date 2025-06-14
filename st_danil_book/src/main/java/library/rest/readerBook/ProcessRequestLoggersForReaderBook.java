package library.rest.readerBook;

import library.core.requests.*;
import library.core.responses.*;
import library.core.services.*;
import library.rest.reader.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
 class ProcessRequestLoggersForReaderBook {

    @Autowired
    private AddReaderBookRequestLogger addReaderBookRequestLogger;
    @Autowired private AddReaderBookResponseLogger addReaderBookResponseLogger;

    @Autowired private DeleteReaderBookRequestLogger deleteReaderBookRequestLogger;
    @Autowired private DeleteReaderBookResponseLogger deleteReaderBookResponseLogger;

    @Autowired private FindAllBooksWhichReadingReaderResponseLogger findAllBooksWhichReadingReaderResponseLogger;
    @Autowired private FindAllBooksWhichReadingReaderRequestLogger findAllBooksWhichReadingReaderRequestLogger;

    @Autowired private AddReaderBookService addReaderBookService;
    @Autowired private DeleteReaderBookService deleteReaderBookService;
    @Autowired private FindAllBooksWhichReadingReaderService findAllBooksWhichReadingReaderService;

    public AddReaderBookResponse processRequestAddReaderBook(AddReaderBookRequest request) {
        addReaderBookRequestLogger.log(request);
        AddReaderBookResponse response = addReaderBookService.execute(request);
       addReaderBookResponseLogger.log(response);
        return response;
    }

    public DeleteReaderBookResponse processRequestRemoveReaderBook(DeleteReaderBookRequest request) {
        deleteReaderBookRequestLogger.log(request);
        DeleteReaderBookResponse response = deleteReaderBookService.execute(request);
        deleteReaderBookResponseLogger.log(response);
        return response;
    }


    public FindAllBooksWhichReadingReaderResponse processRequestSearchBooksWhichReadingReader(FindAllBooksWhichReadingReaderRequest request) {
        findAllBooksWhichReadingReaderRequestLogger.log(request);
        FindAllBooksWhichReadingReaderResponse response = findAllBooksWhichReadingReaderService.execute(request);
        findAllBooksWhichReadingReaderResponseLogger.log(response);
        return response;
    }

}
