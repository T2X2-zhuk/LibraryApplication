package library.core.validators.bookValidation;

import library.core.requests.RemoveBookRequest;
import library.core.validators.LibraryValidatorsValidationTest;
import library.core.validators.bookValidation.RemoveBookRequestValidator;
import library.core.util.ValidationError;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveBookRequestValidatorTest extends LibraryValidatorsValidationTest {

	@Mock BookParametersRequestValidator bookParametersRequestValidator;

	@InjectMocks
	private RemoveBookRequestValidator validator;

	@Test
	public void shouldReturnErrorWhenTitleIsNull() {
		RemoveBookRequest request = mock(RemoveBookRequest.class);
		List<ValidationError> errors = new ArrayList<>();
		when(bookParametersRequestValidator.validateBookId(request.getBookId())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_9","Book id must not be empty!")));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_9");
		assertEquals(errors.get(0).getDescription(), "Book id must not be empty!");
	}

	@Test
	public void shouldSuccess() {
		RemoveBookRequest request = mock(RemoveBookRequest.class);
		List<ValidationError> errors = new ArrayList<>();
		when(bookParametersRequestValidator.validateBookId(request.getBookId())).thenReturn(Optional.empty());
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 0);
	}

}