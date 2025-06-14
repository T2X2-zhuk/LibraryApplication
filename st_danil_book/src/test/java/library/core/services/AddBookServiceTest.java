package library.core.services;
import library.core.database.jpa.JpaBookRepository;
import library.core.requests.AddBookRequest;
import library.core.responses.AddBookResponse;
import library.core.validators.bookValidation.AddBookRequestValidator;
import library.core.util.ValidationError;
import library.matchers.BookMatcher;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceTest extends LibraryServiceValidationTest {

	@Mock private JpaBookRepository database;
	@Mock private AddBookRequestValidator validator;
	@InjectMocks private AddBookService service;

	@Test
	public void shouldReturnResponseWithErrorsWhenValidationFails() {
		AddBookRequest notValidRequest = new AddBookRequest(null, "Author");
		when(validator.validate(notValidRequest)).thenReturn(List.of(new ValidationError("ERROR_CODE_1", "Title must not be empty!")));
		AddBookResponse response = service.execute(notValidRequest);
		assertTrue(response.hasErrors());
	}

	@Test
	public void shouldReturnResponseWithErrorsReceivedFromValidator() {
		AddBookRequest notValidRequest = new AddBookRequest(null, "Author");
		when(validator.validate(notValidRequest)).thenReturn(List.of(new ValidationError("ERROR_CODE_1", "Title must not be empty!")));
		AddBookResponse response = service.execute(notValidRequest);
		assertEquals(response.getErrors().size(), 1);
		assertEquals(response.getErrors().get(0).getErrorCode(), "ERROR_CODE_1");
		assertEquals(response.getErrors().get(0).getDescription(), "Title must not be empty!");
	}

	@Test
	public void shouldNotInvokeDatabaseWhenRequestValidationFails() {
		AddBookRequest notValidRequest = new AddBookRequest(null, "Author");
		when(validator.validate(notValidRequest)).thenReturn(List.of(new ValidationError("ERROR_CODE_1", "Title must not be empty!")));
		service.execute(notValidRequest);
		verifyNoInteractions(database);
	}

	@Test
	public void shouldAddBookToDatabaseWhenRequestIsValid() {
		AddBookRequest validRequest = new AddBookRequest("Title", "Author");
		when(validator.validate(validRequest)).thenReturn(List.of());
		service.execute(validRequest);
		verify(database).save(argThat(new BookMatcher("Title", "Author")));
	}

	@Test
	public void shouldReturnResponseWithoutErrorsWhenRequestIsValid() {
		AddBookRequest validRequest = new AddBookRequest("Title", "Author");
		when(validator.validate(validRequest)).thenReturn(List.of());
		AddBookResponse response = service.execute(validRequest);
		assertFalse(response.hasErrors());
	}

	@Test
	public void shouldReturnResponseWithBookWhenRequestIsValid() {
		AddBookRequest validRequest = new AddBookRequest("Title", "Author");
		when(validator.validate(validRequest)).thenReturn(List.of());
		AddBookResponse response = service.execute(validRequest);
		assertNotNull(response.getNewBook());
		assertEquals(response.getNewBook().getTitle(), validRequest.getTitle());
		assertEquals(response.getNewBook().getAuthor(), validRequest.getAuthor());
	}

}