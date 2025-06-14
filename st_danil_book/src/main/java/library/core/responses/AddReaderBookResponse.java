package library.core.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.ReaderBook;
import library.core.util.ValidationError;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddReaderBookResponse extends CoreResponse{

    private ReaderBook readerBook;

    public AddReaderBookResponse(List<ValidationError> errors) {
        super(errors);
    }

    public AddReaderBookResponse(ReaderBook readerBook) {
        this.readerBook = readerBook;
    }

}
