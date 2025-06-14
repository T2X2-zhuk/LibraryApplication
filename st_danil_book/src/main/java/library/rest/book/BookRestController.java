package library.rest.book;


import com.google.common.base.Stopwatch;
import library.core.requests.AddBookRequest;
import library.core.requests.GetAllBooksRequest;
import library.core.requests.RemoveBookRequest;
import library.core.requests.SearchBooksRequest;
import library.core.responses.AddBookResponse;
import library.core.responses.GetAllBooksResponse;
import library.core.responses.RemoveBookResponse;
import library.core.responses.SearchBooksResponse;
import library.core.services.*;
import library.rest.common.LibraryRestRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired private LibraryRestRequestExecutionTimeLogger timeLogger;
    @Autowired private ProcessRequestLoggersForBook forBook;
    @Autowired private GetAllBooksService service ;

    @PostMapping(path = "/addBook",
            consumes = "application/json",
            produces = "application/json")
    public AddBookResponse addBook(@RequestBody AddBookRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
       AddBookResponse response = forBook.processRequestAddBook(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }


    @DeleteMapping(path = "/deleteBook/{id}", produces = "application/json")
    public RemoveBookResponse deleteBook(@PathVariable("id") Long id) {
       RemoveBookRequest request = new RemoveBookRequest(id);
        Stopwatch stopwatch = Stopwatch.createStarted();
        RemoveBookResponse response = forBook.processRequestRemoveBook(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @GetMapping(path = "/getAllBooks", produces = "application/json")
    public GetAllBooksResponse getBook() {
        GetAllBooksRequest request = new GetAllBooksRequest();
        return service.execute(request);
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchBooksResponse searchBooksPost(@RequestBody SearchBooksRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        SearchBooksResponse response = forBook.processRequestSearchBooksResponse(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchBooksResponse searchBooksGet(@RequestParam String title,
                                              @RequestParam String author) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        SearchBooksRequest request = new SearchBooksRequest(title, author);
        SearchBooksResponse response = forBook.processRequestSearchBooksResponse(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

}
