package library.core.responses;



import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.Book;
import library.core.util.ValidationError;
import lombok.*;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddBookResponse extends CoreResponse {

	private Book newBook;

	public AddBookResponse(List<ValidationError> errors) {
		super(errors);
	}

	public AddBookResponse(Book newBook) {
		this.newBook = newBook;
	}

}