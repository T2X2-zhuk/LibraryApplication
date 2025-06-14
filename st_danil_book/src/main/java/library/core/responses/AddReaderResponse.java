package library.core.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.Reader;
import library.core.util.ValidationError;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddReaderResponse extends CoreResponse{

    private Reader newReader;

    public AddReaderResponse(List<ValidationError> errors) {
        super(errors);
    }

    public AddReaderResponse(Reader newReader) {
        this.newReader = newReader;
    }

}
