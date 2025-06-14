package library.rest.readerBook;

import com.google.common.base.Stopwatch;
import library.core.requests.*;
import library.core.responses.*;
import library.core.services.AddReaderBookService;
import library.core.services.DeleteReaderBookService;
import library.core.services.FindAllBooksWhichReadingReaderService;
import library.core.services.GetAllReaderBooksService;
import library.rest.common.LibraryRestRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/readerBook")
public class ReaderBookRestController {

    @Autowired private ProcessRequestLoggersForReaderBook processRequestLoggersForReaderBook;
    @Autowired private GetAllReaderBooksService getAllReaderBooksService;
    @Autowired private LibraryRestRequestExecutionTimeLogger timeLogger;

    @PostMapping(path = "/addReaderBook",
            consumes = "application/json",
            produces = "application/json")
    public AddReaderBookResponse addReaderBook(@RequestBody AddReaderBookRequest request){
        Stopwatch stopwatch = Stopwatch.createStarted();
        AddReaderBookResponse response = processRequestLoggersForReaderBook.processRequestAddReaderBook(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @DeleteMapping(path = "/deleteReader",consumes = "application/json",produces = "application/json")
    public DeleteReaderBookResponse deleteReaderBook(@RequestBody DeleteReaderBookRequest request){
        Stopwatch stopwatch = Stopwatch.createStarted();
        DeleteReaderBookResponse response = processRequestLoggersForReaderBook.processRequestRemoveReaderBook(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @GetMapping(path = "/getAllReaderBooks", produces = "application/json")
    public GetAllReaderBooksResponse getReaderBooks() {
        return getAllReaderBooksService.execute();
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public FindAllBooksWhichReadingReaderResponse searchBooksWhichReadingReaderPost(@RequestBody FindAllBooksWhichReadingReaderRequest request) {
        Stopwatch stopwatch =Stopwatch.createStarted();
        FindAllBooksWhichReadingReaderResponse response = processRequestLoggersForReaderBook.processRequestSearchBooksWhichReadingReader(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @GetMapping(path = "/search", produces = "application/json")
    public FindAllBooksWhichReadingReaderResponse searchBooksWhichReadingReaderGet(@RequestBody FindAllBooksWhichReadingReaderRequest request) {
        Stopwatch stopwatch =Stopwatch.createStarted();
        FindAllBooksWhichReadingReaderResponse response = processRequestLoggersForReaderBook.processRequestSearchBooksWhichReadingReader(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
