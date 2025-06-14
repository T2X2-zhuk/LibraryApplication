package library.rest.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.core.requests.GetAllBooksRequest;
import library.core.requests.SearchBooksRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
 class SearchBooksRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(SearchBooksRequestLogger.class);

    void log(SearchBooksRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }
}
