package library.core.services;

import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Reader;
import library.core.requests.Ordering;
import library.core.requests.Paging;
import library.core.requests.SearchReadersRequest;
import library.core.responses.SearchReaderResponse;
import library.core.validators.readerValidation.SearchReadersRequestValidator;
import library.core.util.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchReadersServiceTest extends LibraryServiceValidationTest{
    @Mock
    private JpaReaderRepository repository;
    @Mock
    private SearchReadersRequestValidator validator;

    @InjectMocks
    SearchReadersService service;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(service, "orderingEnabled", true);
        ReflectionTestUtils.setField(service, "pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        SearchReadersRequest request = new SearchReadersRequest(null, null);
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("ERROR_CODE_7", "First name must not be empty!"));
        errors.add(new ValidationError("ERROR_CODE_8", "Last name must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchReaderResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(repository);
    }

    @Test
    public void shouldSearchByFirstName() {
        SearchReadersRequest request = new SearchReadersRequest("Title", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Title", "Author"));
        Mockito.when(repository.findByFirstName("Title")).thenReturn(readers);

        SearchReaderResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getReaderList().size(), 1);
        assertEquals(response.getReaderList().get(0).getFirst_name(), "Title");
        assertEquals(response.getReaderList().get(0).getLast_name(), "Author");
    }

    @Test
    public void shouldSearchByLastName() {
        SearchReadersRequest request = new SearchReadersRequest(null, "LastName");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Title", "Author"));
        Mockito.when(repository.findByLastName("LastName")).thenReturn(readers);

        SearchReaderResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getReaderList().size(), 1);
        assertEquals(response.getReaderList().get(0).getFirst_name(), "Title");
        assertEquals(response.getReaderList().get(0).getLast_name(), "Author");
    }

    @Test
    public void shouldSearchByFirstNameWithOrderingAscending() {
        Ordering ordering = new Ordering("FirstName", "ASCENDING");
        SearchReadersRequest request = new SearchReadersRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Title", "Author2"));
        readers.add(new Reader("Title", "Author1"));
        Mockito.when(repository.findByFirstName("Title")).thenReturn(readers);

        SearchReaderResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getReaderList().size(), 2);
        assertEquals(response.getReaderList().get(0).getLast_name(), "Author1");
        assertEquals(response.getReaderList().get(1).getLast_name(), "Author2");
    }

    @Test
    public void shouldSearchByFirstNameWithOrderingDescending() {
        Ordering ordering = new Ordering("FirstName", "DESCENDING");
        SearchReadersRequest request = new SearchReadersRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Title", "Author2"));
        readers.add(new Reader("Title", "Author1"));
        Mockito.when(repository.findByFirstName("Title")).thenReturn(readers);

        SearchReaderResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getReaderList().size(), 2);
        assertEquals(response.getReaderList().get(0).getLast_name(), "Author2");
        assertEquals(response.getReaderList().get(1).getLast_name(), "Author1");
    }

    @Test
    public void shouldSearchByFirstNameWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchReadersRequest request = new SearchReadersRequest("Title", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Title", "Author1"));
       readers.add(new Reader("Title", "Author2"));
        Mockito.when(repository.findByFirstName("Title")).thenReturn(readers);

        SearchReaderResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getReaderList().size(), 1);
        assertEquals(response.getReaderList().get(0).getFirst_name(), "Title");
        assertEquals(response.getReaderList().get(0).getLast_name(), "Author1");
    }

    @Test
    public void shouldSearchByTitleWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchReadersRequest request = new SearchReadersRequest("Title", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Title", "Author1"));
        readers.add(new Reader("Title", "Author2"));
        Mockito.when(repository.findByFirstName("Title")).thenReturn(readers);

        SearchReaderResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getReaderList().size(), 1);
        assertEquals(response.getReaderList().get(0).getFirst_name(), "Title");
        assertEquals(response.getReaderList().get(0).getLast_name(), "Author2");
    }
}
