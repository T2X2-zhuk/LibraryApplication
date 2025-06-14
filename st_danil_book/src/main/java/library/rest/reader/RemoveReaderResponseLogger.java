package library.rest.reader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.core.requests.AddBookRequest;
import library.core.responses.RemoveReaderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
 class RemoveReaderResponseLogger {

    private static final Logger logger = LoggerFactory.getLogger(RemoveReaderResponse.class);

    void log(RemoveReaderResponse request) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            logger.info("REQUEST: " + json);
        } catch (JsonProcessingException e) {
            logger.error("Error to convert request to JSON", e);
        }
    }
}
