package library.core.services;

import library.core.database.jpa.JpaReaderRepository;
import library.core.requests.AddReaderRequest;
import library.core.responses.AddReaderResponse;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import library.core.util.ValidationError;
import library.matchers.ReaderMatcher;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddReaderServiceTest extends LibraryServiceValidationTest{

    @Mock
    private JpaReaderRepository repository;
    @Mock
    private AddReaderRequestValidator validator;
    @InjectMocks
    private AddReaderService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails(){
        AddReaderRequest notValidRequest = new AddReaderRequest(null,"LastName");
        when(validator.validate(notValidRequest)).
                thenReturn(List.of(new ValidationError("ERROR_CODE_7","First name must not be empty!")));
        AddReaderResponse response = service.execute(notValidRequest);
        Assert.assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnAddReaderToDatabaseWhenRequestIsValid(){
        AddReaderRequest validRequest = new AddReaderRequest("null","LastName");
        when(validator.validate(validRequest)).
                thenReturn(List.of());
        service.execute(validRequest);
       verify(repository).save(argThat(new ReaderMatcher("null","LastName")));
    }

    @Test
    public void shouldReturnResponseWithReaderWhenRequestIsValid() {
        AddReaderRequest request = new AddReaderRequest("Title", "Author");
        when(validator.validate(request)).thenReturn(List.of());
        AddReaderResponse response = service.execute(request);
        assertNotNull(response.getNewReader());
        assertEquals(response.getNewReader().getFirst_name(), request.getFirstName());
        assertEquals(response.getNewReader().getLast_name(), request.getLastName());
    }
}
