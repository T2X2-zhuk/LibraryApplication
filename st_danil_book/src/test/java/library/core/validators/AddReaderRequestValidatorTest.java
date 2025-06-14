package library.core.validators;

import library.core.requests.AddReaderRequest;
import library.core.validators.readerBookValidation.AddReaderBookRequestValidator;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import library.core.util.ValidationError;
import library.core.validators.readerValidation.ReaderParametersRequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddReaderRequestValidatorTest extends LibraryValidatorsValidationTest {

    @Mock private ReaderParametersRequestValidator readerParametersRequestValidator;
    @InjectMocks
    private AddReaderRequestValidator validator;

 @Test
    public void shouldReturnErrorWhenFirstNameIsNull(){
        AddReaderRequest request = mock(AddReaderRequest.class);
        when(readerParametersRequestValidator.validateFirstName(request.getFirstName())).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
     }

    @Test
    public void shouldReturnErrorWhenLastNameIsNull(){
        AddReaderRequest request = mock(AddReaderRequest.class);
        when(readerParametersRequestValidator.validateLastName(request.getLastName())).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);   }

    @Test
    public void shouldReturnErrorWhenFirstNameAndLastNameIsNull(){
        AddReaderRequest request = mock(AddReaderRequest.class);
        when(readerParametersRequestValidator.validateLastName(request.getLastName())).thenReturn(Optional.of(new ValidationError()));
        when(readerParametersRequestValidator.validateFirstName(request.getFirstName())).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
    }

    @Test
    public void shouldSuccess() {
        AddReaderRequest request = mock(AddReaderRequest.class);
        when(readerParametersRequestValidator.validateLastName(request.getLastName())).thenReturn(Optional.empty());
        when(readerParametersRequestValidator.validateFirstName(request.getFirstName())).thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}
