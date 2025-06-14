package library.rest.reader;

import library.core.requests.*;
import library.core.responses.*;
import library.core.services.*;
import library.rest.book.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
 class ProcessRequestLoggersForReader {

    @Autowired
    private AddReaderRequestLogger addReaderRequestLogger;
    @Autowired private AddReaderResponseLogger addReaderResponseLogger;

    @Autowired private RemoveReaderRequestLogger removeReaderRequestLogger;
    @Autowired private RemoveReaderResponseLogger removeReaderResponseLogger;

    @Autowired private SearchReadersRequestLogger searchReadersRequestLogger;
    @Autowired private SearchReaderResponseLogger searchReaderResponseLogger;

    @Autowired private AddReaderService addReaderService;
    @Autowired private RemoveReaderService removeReaderService;
    @Autowired private GetAllReadersService getAllReadersService;
    @Autowired private SearchReadersService searchReadersService;

    public AddReaderResponse processRequestAddReader(AddReaderRequest request) {
        addReaderRequestLogger.log(request);
        AddReaderResponse response = addReaderService.execute(request);
        addReaderResponseLogger.log(response);
        return response;
    }

    public RemoveReaderResponse processRequestRemoveReader(RemoveReaderRequest request) {
        removeReaderRequestLogger.log(request);
        RemoveReaderResponse response = removeReaderService.execute(request);
        removeReaderResponseLogger.log(response);
        return response;
    }


    public SearchReaderResponse processRequestSearchReadersResponse(SearchReadersRequest request) {
        searchReadersRequestLogger.log(request);
        SearchReaderResponse response = searchReadersService.execute(request);
        searchReaderResponseLogger.log(response);
        return response;
    }

}
