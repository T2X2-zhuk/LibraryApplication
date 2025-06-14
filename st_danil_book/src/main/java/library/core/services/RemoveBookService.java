package library.core.services;


import library.core.database.jpa.JpaBookRepository;
import library.core.domain.Book;
import library.core.requests.RemoveBookRequest;
import library.core.responses.RemoveBookResponse;
import library.core.validators.bookValidation.RemoveBookRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class RemoveBookService {

	@Autowired private JpaBookRepository database;
	@Autowired private RemoveBookRequestValidator validator;

	@Transactional
	public RemoveBookResponse execute(RemoveBookRequest request) {
		List<ValidationError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new RemoveBookResponse(errors);
		}
		Optional<Book> book = database.findById(request.getBookId());
		if (book.isEmpty()){
			return new RemoveBookResponse(false);
		}
		database.delete(book.get());
		return new RemoveBookResponse(true);
	}

}