package library.core.services;

import library.core.database.jpa.JpaBookRepository;
import library.core.domain.Book;
import library.core.requests.AddBookRequest;
import library.core.responses.AddBookResponse;
import library.core.validators.bookValidation.AddBookRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class AddBookService {

	@Autowired	private JpaBookRepository database;
	@Autowired private AddBookRequestValidator validator;

	@Transactional
	public AddBookResponse execute(AddBookRequest request) {
		List<ValidationError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new AddBookResponse(errors);
		}

		Book book = new Book(request.getTitle(), request.getAuthor());
		database.save(book);
		return new AddBookResponse(book);
	}

}
