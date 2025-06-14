package library.core.services;

import library.core.database.jpa.JpaBookRepository;
import library.core.domain.Book;
import library.core.requests.GetAllBooksRequest;
import library.core.responses.GetAllBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetAllBooksService {

	@Autowired private JpaBookRepository database;

	@Transactional
	public GetAllBooksResponse execute(GetAllBooksRequest request) {
		List<Book> books = database.getAllBooks();
		return new GetAllBooksResponse(books);
	}

}