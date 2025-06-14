package library.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.util.ValidationError;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteReaderBookResponse extends CoreResponse{

    private boolean readerRemove;

    public DeleteReaderBookResponse(List<ValidationError> errors) {
        super(errors);

    }

    public DeleteReaderBookResponse(boolean readerRemove) {
        this.readerRemove = readerRemove;
    }

    public boolean isReaderRemove() {
        return readerRemove;
    }
}
