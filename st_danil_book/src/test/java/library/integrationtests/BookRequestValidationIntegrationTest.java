package library.integrationtests;

import library.core.requests.*;
import library.core.util.ValidationError;
import library.core.validators.bookValidation.AddBookRequestValidator;

import library.core.validators.bookValidation.RemoveBookRequestValidator;
import library.core.validators.bookValidation.SearchBooksRequestValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookRequestValidationIntegrationTest extends IntegrationClassConfig {

	@Autowired private AddBookRequestValidator addBookRequestValidator;
	@Autowired private SearchBooksRequestValidator searchBooksRequestValidator;
	@Autowired private RemoveBookRequestValidator removeBookRequestValidator;
	@Test
	public void shouldReturnErrorWhenTitleIsNull() {
		AddBookRequest request = AddBookRequest.builder().title(null).author("Jhabs").build();
		List<ValidationError> errors = addBookRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_1");
		assertEquals(errors.get(0).getDescription(), "Title must not be empty!");

	}

	@Test
	public void shouldReturnErrorWhenAuthorIsNull() {
		AddBookRequest request = AddBookRequest.builder().title("s,s").author(null).build();
		List<ValidationError> errors = addBookRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_2");
		assertEquals(errors.get(0).getDescription(), "Author must not be empty!");

	}

	@Test
	public void shouldReturnErrorWhenIdIsNull() {
		RemoveBookRequest request = RemoveBookRequest.builder().bookId(null).build();
		List<ValidationError> errors = removeBookRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_9");

	}

	@Test
	public void shouldReturnErrorWhenOrderingOrderByIsNull() {
		SearchBooksRequest request = SearchBooksRequest.builder().author("xslx").title("scxsc").ordering(Ordering.builder().orderBy(null).orderDirection("ASCENDING").build()).paging(Paging.builder().pageNumber(1).pageSize(2).build()).build();
		List<ValidationError> errors = searchBooksRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_13");

	}

	@Test
	public void shouldReturnErrorWhenOrderingOrderDirectionIsNull() {
		SearchBooksRequest request = SearchBooksRequest.builder().author("xslx").title("scxsc").ordering(Ordering.builder().orderBy("Title").orderDirection(null).build()).paging(Paging.builder().pageNumber(1).pageSize(2).build()).build();
		List<ValidationError> errors = searchBooksRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_14");

	}
	@Test
	public void shouldReturnErrorWhenOrderingPageNumberMustBeMore0() {
		SearchBooksRequest request = SearchBooksRequest.builder().author("xslx").title("scxsc").ordering(Ordering.builder().orderBy("Title").orderDirection("ASCENDING").build()).paging(Paging.builder().pageNumber(0).pageSize(2).build()).build();
		List<ValidationError> errors = searchBooksRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_15");

	}

	@Test
	public void shouldReturnErrorWhenOrderingPageSizeMustBeMore0() {
		SearchBooksRequest request = SearchBooksRequest.builder().author("xslx").title("scxsc").ordering(Ordering.builder().orderBy("Title").orderDirection("ASCENDING").build()).paging(Paging.builder().pageNumber(1).pageSize(0).build()).build();
		List<ValidationError> errors = searchBooksRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_16");

	}

	@Test
	public void shouldReturnErrorWhenOrderingPageSizeIsNull() {
		SearchBooksRequest request = SearchBooksRequest.builder().author("xslx").title("scxsc").ordering(Ordering.builder().orderBy("Title").orderDirection("ASCENDING").build()).paging(Paging.builder().pageNumber(1).pageSize(null).build()).build();
		List<ValidationError> errors = searchBooksRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_18");

	}

	@Test
	public void shouldReturnErrorWhenOrderingPageNumberIsNull() {
		SearchBooksRequest request = SearchBooksRequest.builder().author("xslx").title("scxsc").ordering(Ordering.builder().orderBy("Title").orderDirection("ASCENDING").build()).paging(Paging.builder().pageNumber(null).pageSize(2).build()).build();
		List<ValidationError> errors = searchBooksRequestValidator.validate(request);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_17");

	}

}
