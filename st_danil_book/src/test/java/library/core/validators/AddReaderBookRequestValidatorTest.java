package library.core.validators;

import library.core.domain.Reader;
import library.core.requests.AddReaderBookRequest;
import library.core.validators.bookValidation.AddBookRequestValidator;
import library.core.validators.bookValidation.BookParametersRequestValidator;
import library.core.validators.readerBookValidation.AddReaderBookRequestValidator;
import library.core.util.ValidationError;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import library.core.validators.readerValidation.ReaderParametersRequestValidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
public class AddReaderBookRequestValidatorTest extends LibraryValidatorsValidationTest{

    @Mock private AddBookRequestValidator validator1;
    @Mock private AddReaderRequestValidator validator2;
    @Mock private ReaderParametersRequestValidator readerParametersRequestValidator;
    @Mock private BookParametersRequestValidator validator3;
    @InjectMocks
    private AddReaderBookRequestValidator validator;

    @Test
    public void shouldBeReturnErrorsWithIsNullBook_out_DateAndBook_return_Date(){
        AddReaderBookRequest request = mock(AddReaderBookRequest.class);
        List<ValidationError> errors = new ArrayList<>();
        when(readerParametersRequestValidator.validateBook_out_date(request.getBook_out_date())).thenReturn(Optional.of(new ValidationError()));
        when(readerParametersRequestValidator.validateBook_return_date(request.getBook_out_date())).thenReturn(Optional.of(new ValidationError()));
        errors.addAll(validator.validate(request));
        assertEquals(errors.size(), 2);
    }
}
