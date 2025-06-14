package library.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.Book;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllBooksResponse extends CoreResponse {

	private List<Book> books;

	public GetAllBooksResponse(List<Book> books) {
		this.books = books;
	}

}
