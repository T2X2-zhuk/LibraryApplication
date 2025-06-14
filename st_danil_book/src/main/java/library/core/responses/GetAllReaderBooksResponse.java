package library.core.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.ReaderBook;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllReaderBooksResponse extends CoreResponse{

    private List<ReaderBook> readerBooks;

    public GetAllReaderBooksResponse(List<ReaderBook> readerBooks) {
        this.readerBooks = readerBooks;
    }

}
