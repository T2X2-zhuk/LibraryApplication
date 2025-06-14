package library.core.validators.bookValidation;

import library.core.requests.Ordering;
import library.core.requests.Paging;
import library.core.requests.SearchBooksRequest;
import library.core.validators.LibraryValidatorsValidationTest;
import library.core.validators.bookValidation.SearchBooksRequestValidator;
import library.core.util.ValidationError;
import library.core.validators.orderingAndPagingValidate.OrderAndPageValidator;
import library.core.validators.readerValidation.RemoveReaderRequestValidator;
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
public class SearchBooksRequestValidatorTest extends LibraryValidatorsValidationTest {

	@Mock
	private BookParametersRequestValidator bookParametersRequestValidator;
	@Mock private OrderAndPageValidator orderAndPageValidator;

	@InjectMocks
	private SearchBooksRequestValidator validator;

	@Test
	public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
		Ordering ordering = mock(Ordering.class);
		Paging paging = mock(Paging.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getOrdering()).thenReturn(ordering);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validateMandatoryOrderDirection(request.getOrdering())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_14","Order direction must not be empty!")));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_14");
		assertEquals(errors.get(0).getDescription(), "Order direction must not be empty!");
	}
	@Test
	public void shouldReturnErrorWhenOrderByAreEmpty() {
		Ordering ordering = mock(Ordering.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getOrdering()).thenReturn(ordering);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validateMandatoryOrderBy(request.getOrdering())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_13","Order by must not be empty!")));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_13");
		assertEquals(errors.get(0).getDescription(), "Order by must not be empty!");

	}

	@Test
	public void shouldReturnErrorWhenOrderByContainNotValidValue() {
		Ordering ordering = mock(Ordering.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getOrdering()).thenReturn(ordering);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validateOrderBy2(request.getOrdering())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_19","Order by must contain 'author' or 'title' only!")));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_19");
		assertEquals(errors.get(0).getDescription(), "Order by must contain 'author' or 'title' only!");
	}

	@Test
	public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
		Ordering ordering = mock(Ordering.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getOrdering()).thenReturn(ordering);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validateOrderDirection(request.getOrdering())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_12","Order direction must contain 'ASCENDING' or 'DESCENDING' only!")));
		errors.addAll(validator.validate(request));	assertEquals(errors.size(), 1);
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_12");
		assertEquals(errors.get(0).getDescription(), "Order direction must contain 'ASCENDING' or 'DESCENDING' only!");
	}


	@Test
	public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
		Paging paging = mock(Paging.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getPaging()).thenReturn(paging);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validatePageNumber(request.getPaging())).thenReturn(Optional.of(new ValidationError("ERROR_CODE_15","Page number must be more 0!")));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
		assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_15");
		assertEquals(errors.get(0).getDescription(), "Page number must be more 0!");
	}


	@Test
	public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
		Paging paging = mock(Paging.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getPaging()).thenReturn(paging);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validatePageSize(request.getPaging())).thenReturn(Optional.of(new ValidationError()));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
	}

    @Test
	public void shouldReturnErrorWhenPageNumberAreEmpty() {
		Paging paging = mock(Paging.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getPaging()).thenReturn(paging);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validateMandatoryPageNumber(request.getPaging())).thenReturn(Optional.of(new ValidationError()));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
	}

	@Test
	public void shouldReturnErrorWhenPageSizeAreEmpty() {
		Paging paging = mock(Paging.class);
		SearchBooksRequest request = mock(SearchBooksRequest.class);
		when(request.getPaging()).thenReturn(paging);
		List<ValidationError> errors = new ArrayList<>();
		when(orderAndPageValidator.validateMandatoryPageSize(request.getPaging())).thenReturn(Optional.of(new ValidationError()));
		errors.addAll(validator.validate(request));
		assertEquals(errors.size(), 1);
	}

}