package library.rest.readerBook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.core.requests.FindAllBooksWhichReadingReaderRequest;
import library.core.responses.FindAllBooksWhichReadingReaderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FindAllBooksWhichReadingReaderRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(FindAllBooksWhichReadingReaderRequest.class);

    void log(FindAllBooksWhichReadingReaderRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }
}
