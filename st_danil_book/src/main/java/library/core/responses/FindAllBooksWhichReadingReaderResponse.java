package library.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.Book;
import library.core.util.ValidationError;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindAllBooksWhichReadingReaderResponse extends CoreResponse{

    private List<Book>  booksList;

    public FindAllBooksWhichReadingReaderResponse(List<ValidationError> errors, List<Book> booksList) {
        super(errors);
        this.booksList = booksList;
    }
}
