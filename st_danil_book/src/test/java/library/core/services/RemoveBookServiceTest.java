package library.core.services;

import library.core.database.jpa.JpaBookRepository;
import library.core.requests.RemoveBookRequest;
import library.core.responses.RemoveBookResponse;
import library.core.validators.bookValidation.RemoveBookRequestValidator;
import library.core.util.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RemoveBookServiceTest extends LibraryServiceValidationTest{

	@Mock private JpaBookRepository database;
	@Mock private RemoveBookRequestValidator validator;
	@InjectMocks private RemoveBookService service;

	@Test
	public void shouldReturnErrorWhenBookIdNotProvided() {
		RemoveBookRequest request = new RemoveBookRequest(null);
		List<ValidationError> errors = new ArrayList<>();
		errors.add(new ValidationError("ERROR_CODE_9", "Book id must not be empty!"));
		Mockito.when(validator.validate(request)).thenReturn(errors);
		RemoveBookResponse response = service.execute(request);
		assertTrue(response.hasErrors());
		assertEquals(response.getErrors().size(), 1);
		assertEquals(response.getErrors().get(0).getErrorCode(), "ERROR_CODE_9");
		assertEquals(response.getErrors().get(0).getDescription(), "Book id must not be empty!");
	}


}