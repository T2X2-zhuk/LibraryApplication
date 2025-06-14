package library.core.validators.bookValidation;

import library.core.requests.AddBookRequest;
import library.core.validators.LibraryValidatorsValidationTest;
import library.core.util.ValidationError;
import library.core.validators.ValidationErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddBookRequestValidatorTest extends LibraryValidatorsValidationTest {

    @Mock private BookParametersRequestValidator validator;

	@InjectMocks
	private AddBookRequestValidator bookRequestValidator;



	@Test
	public void shouldReturnErrorWhenTitleIsNull() {
		AddBookRequest request = mock(AddBookRequest.class);
		when(validator.validateTitle(request.getTitle())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_1","Title must not be empty!")));
		List<ValidationError> errors = bookRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_1");
		assertEquals(errors.get(0).getDescription(), "Title must not be empty!");
	}

	@Test
	public void shouldReturnErrorWhenAuthorIsNull() {
        AddBookRequest request = mock(AddBookRequest.class);
        when(validator.validateTitle(request.getAuthor())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_2","Author must not be empty!")));
        List<ValidationError> errors = bookRequestValidator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_2");
        assertEquals(errors.get(0).getDescription(), "Author must not be empty!");

    }

	@Test
	public void shouldReturnErrorsWhenAuthorAndTitleIsNull() {
		AddBookRequest request = mock(AddBookRequest.class);
        List<ValidationError> errors = new ArrayList<>();
        when(validator.validateAuthor(request.getAuthor())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_2","Author must not be empty!")));
        when(validator.validateTitle(request.getTitle())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_1","Title must not be empty!")));
        errors.addAll(bookRequestValidator.validate(request));
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getErrorCode(), "ERROR_CODE_2");
        assertEquals(errors.get(1).getDescription(), "Author must not be empty!");

        assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_1");
        assertEquals(errors.get(0).getDescription(), "Title must not be empty!");
    }

	@Test
	public void shouldSuccess() {
        AddBookRequest request = mock(AddBookRequest.class);
        List<ValidationError> errors = new ArrayList<>();
        when(validator.validateTitle(request.getAuthor())).thenReturn(Optional.empty());
        when(validator.validateTitle(request.getTitle())).thenReturn(Optional.empty());
        errors.addAll(bookRequestValidator.validate(request));
        assertEquals(errors.size(), 0);
    }



}

