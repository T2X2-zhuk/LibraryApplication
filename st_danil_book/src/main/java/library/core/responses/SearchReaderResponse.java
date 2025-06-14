package library.core.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.Reader;
import library.core.util.ValidationError;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchReaderResponse extends CoreResponse{

    private List<Reader> readerList;

    public SearchReaderResponse(List<ValidationError> errors, List<Reader> readerList) {
        super(errors);
        this.readerList = readerList;
    }
}
