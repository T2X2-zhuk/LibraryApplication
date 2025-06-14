package library.core.validators.bookValidation;

import library.core.requests.AddBookRequest;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddBookRequestValidator {

	@Autowired private BookParametersRequestValidator validator;

	public List<ValidationError> validate(AddBookRequest request) {
		List<ValidationError> errors = new ArrayList<>();
		validator.validateTitle(request.getTitle()).ifPresent(errors::add);
		validator.validateAuthor(request.getAuthor()).ifPresent(errors::add);
		return errors;
	}

}
