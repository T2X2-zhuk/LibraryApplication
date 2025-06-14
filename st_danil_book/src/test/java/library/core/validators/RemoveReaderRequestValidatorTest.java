package library.core.validators;

import library.core.requests.RemoveReaderRequest;
import library.core.validators.bookValidation.RemoveBookRequestValidator;
import library.core.validators.readerValidation.ReaderParametersRequestValidator;
import library.core.validators.readerValidation.RemoveReaderRequestValidator;
import library.core.util.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RemoveReaderRequestValidatorTest extends LibraryValidatorsValidationTest{

    @Mock
    private ReaderParametersRequestValidator requestValidator;
    @InjectMocks
    private RemoveReaderRequestValidator validator;

    @Test
    public void shouldReturnErrorWhenIdIsNull(){
        RemoveReaderRequest request = mock(RemoveReaderRequest.class);
        when(requestValidator.validatorReaderId(request.getReaderId())).thenReturn(Optional.of(new ValidationError()));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(),1);
    }

    @Test
    public void shouldSuccess(){
        RemoveReaderRequest request = mock(RemoveReaderRequest.class);
        when(requestValidator.validatorReaderId(request.getReaderId())).thenReturn(Optional.empty());
        List<ValidationError> errors = validator.validate(request);
        assertEquals(errors.size(),0);
    }

}
