package library.rest.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.core.requests.FindAllBooksWhichReadingReaderRequest;
import library.core.responses.SearchBooksResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
 class SearchBooksResponseLogger {

    private static final Logger logger = LoggerFactory.getLogger(SearchBooksResponseLogger.class);

    void log(SearchBooksResponse request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }
}
