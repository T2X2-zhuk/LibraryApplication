package library.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.Book;
import library.core.util.ValidationError;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchBooksResponse extends CoreResponse {

	private List<Book> books;

	public SearchBooksResponse(List<Book> books, List<ValidationError> errors) {
		super(errors);
		this.books = books;
	}

}
