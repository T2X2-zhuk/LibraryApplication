package library.core.validators.bookValidation;
import library.core.requests.RemoveBookRequest;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RemoveBookRequestValidator {

	@Autowired
	private BookParametersRequestValidator validator;

	public List<ValidationError> validate(RemoveBookRequest request) {
		List<ValidationError> errors = new ArrayList<>();
		validator.validateBookId(request.getBookId()).ifPresent(errors::add);
		return errors;
	}
}
