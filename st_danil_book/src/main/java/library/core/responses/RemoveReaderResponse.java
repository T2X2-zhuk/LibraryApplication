package library.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.util.ValidationError;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemoveReaderResponse extends CoreResponse{

    private boolean readerRemove;

    public RemoveReaderResponse(List<ValidationError> errors) {
        super(errors);
    }

    public RemoveReaderResponse(boolean readerRemove) {
        this.readerRemove = readerRemove;
    }

}
