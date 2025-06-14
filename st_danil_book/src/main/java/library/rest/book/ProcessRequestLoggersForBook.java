package library.rest.book;

import library.core.requests.AddBookRequest;
import library.core.requests.GetAllBooksRequest;
import library.core.requests.RemoveBookRequest;
import library.core.requests.SearchBooksRequest;
import library.core.responses.AddBookResponse;
import library.core.responses.GetAllBooksResponse;
import library.core.responses.RemoveBookResponse;
import library.core.responses.SearchBooksResponse;
import library.core.services.AddBookService;
import library.core.services.GetAllBooksService;
import library.core.services.RemoveBookService;
import library.core.services.SearchBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
 class ProcessRequestLoggersForBook {

    @Autowired
    private AddBookRequestLogger addBookRequestLogger;
    @Autowired private AddBookResponseLogger addBookResponseLogger;
    @Autowired private RemoveBookRequestLogger removeBookRequestLogger;
    @Autowired private RemoveBookResponseLogger removeBookResponseLogger;

    @Autowired private SearchBooksRequestLogger searchBooksRequestLogger;
    @Autowired private SearchBooksResponseLogger searchBooksResponseLogger;

    @Autowired private AddBookService addBookService;
    @Autowired private RemoveBookService removeBookService;
    @Autowired private GetAllBooksService getAllBooksService;
    @Autowired private SearchBooksService searchBooksService;

    public AddBookResponse processRequestAddBook(AddBookRequest request) {
        addBookRequestLogger.log(request);
        AddBookResponse response = addBookService.execute(request);
        addBookResponseLogger.log(response);
        return response;
    }

    public RemoveBookResponse processRequestRemoveBook(RemoveBookRequest request) {
        removeBookRequestLogger.log(request);
        RemoveBookResponse response = removeBookService.execute(request);
        removeBookResponseLogger.log(response);
        return response;
    }


    public SearchBooksResponse processRequestSearchBooksResponse(SearchBooksRequest request) {
        searchBooksRequestLogger.log(request);
        SearchBooksResponse response = searchBooksService.execute(request);
        searchBooksResponseLogger.log(response);
        return response;
    }

}
