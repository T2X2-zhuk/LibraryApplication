package library.rest.reader;

import com.google.common.base.Stopwatch;
import library.core.requests.*;
import library.core.responses.*;
import library.core.services.AddReaderService;
import library.core.services.GetAllReadersService;
import library.core.services.RemoveReaderService;
import library.core.services.SearchReadersService;
import library.rest.common.LibraryRestRequestExecutionTimeLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reader")
public class ReaderRestController {

    @Autowired private ProcessRequestLoggersForReader processRequestLoggersForReader;
    @Autowired private LibraryRestRequestExecutionTimeLogger timeLogger;
    @Autowired private GetAllReadersService getAllReadersService;
    @PostMapping(path = "/addReader",
            consumes = "application/json",
            produces = "application/json")
    public AddReaderResponse addReader(@RequestBody AddReaderRequest request){
        Stopwatch stopwatch = Stopwatch.createStarted();
        AddReaderResponse response = processRequestLoggersForReader.processRequestAddReader(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @DeleteMapping(path = "/deleteReader/{id}",produces = "application/json")
    public RemoveReaderResponse deleteReader(@PathVariable("id") Long id){
        Stopwatch stopwatch = Stopwatch.createStarted();
        RemoveReaderRequest request = new RemoveReaderRequest(id);
        RemoveReaderResponse response = processRequestLoggersForReader.processRequestRemoveReader(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @GetMapping(path = "/getAllReaders", produces = "application/json")
    public GetAllReadersResponse getReaders() {
        return getAllReadersService.execute(new GetAllReadersRequest());
    }

    @PostMapping(path = "/search",
            consumes = "application/json",
            produces = "application/json")
    public SearchReaderResponse searchReadersPost(@RequestBody SearchReadersRequest request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        SearchReaderResponse response = processRequestLoggersForReader.processRequestSearchReadersResponse(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }

    @GetMapping(path = "/search", produces = "application/json")
    public SearchReaderResponse searchReadersGet(@RequestParam String firstName,
                                              @RequestParam String lastName) {
        Stopwatch stopwatch =Stopwatch.createStarted();
        SearchReadersRequest request = new  SearchReadersRequest(firstName, lastName);
        SearchReaderResponse response = processRequestLoggersForReader.processRequestSearchReadersResponse(request);
        timeLogger.logExecutionTime(stopwatch);
        return response;
    }
}
