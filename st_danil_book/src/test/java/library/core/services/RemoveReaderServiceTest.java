package library.core.services;

import library.core.database.jpa.JpaReaderRepository;
import library.core.requests.RemoveReaderRequest;
import library.core.responses.RemoveReaderResponse;
import library.core.validators.readerValidation.RemoveReaderRequestValidator;
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
public class RemoveReaderServiceTest extends LibraryServiceValidationTest{

    @Mock private JpaReaderRepository repository;

    @Mock private RemoveReaderRequestValidator validator;

    @InjectMocks private RemoveReaderService service;

    @Test
    public void shouldReturnErrorWhenReaderIdNotProvided() {
        RemoveReaderRequest request = new RemoveReaderRequest(null);
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("ERROR_CODE_10", "Reader id must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        RemoveReaderResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getErrorCode(), "ERROR_CODE_10");
        assertEquals(response.getErrors().get(0).getDescription(), "Reader id must not be empty!");
    }

    @Test
    public void shouldDeleteReaderWithIdFromDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        RemoveReaderRequest request = new RemoveReaderRequest(1L);
        RemoveReaderResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isReaderRemove());
    }
}
