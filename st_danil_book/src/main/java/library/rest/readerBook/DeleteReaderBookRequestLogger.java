package library.rest.readerBook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.core.requests.AddReaderBookRequest;
import library.core.requests.DeleteReaderBookRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
 class DeleteReaderBookRequestLogger {

    private static final Logger logger = LoggerFactory.getLogger(DeleteReaderBookRequestLogger.class);

    void log(DeleteReaderBookRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }
}
