package library.integrationtests;

import library.core.requests.AddReaderRequest;
import library.core.requests.Ordering;
import library.core.requests.Paging;
import library.core.requests.SearchReadersRequest;
import library.core.util.ValidationError;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import library.core.validators.readerValidation.SearchReadersRequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReaderRequestValidationIntegrationTest extends IntegrationClassConfig{

    @Autowired private AddReaderRequestValidator validator;
    @Autowired private SearchReadersRequestValidator searchReadersRequestValidator;
    @Test
    public void shouldReturnErrorWhenFirstNameIsNull(){
        AddReaderRequest request  = AddReaderRequest.builder().firstName(null).lastName("ssdd").build();
        List<ValidationError> errors = validator.validate(request);
        Assertions.assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_7");
    }

    @Test
    public void shouldReturnErrorWhenLastNameIsNull(){
        AddReaderRequest request  = AddReaderRequest.builder().firstName("smm").lastName(null).build();
        List<ValidationError> errors = validator.validate(request);
        Assertions.assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_8");
    }

    @Test
    public void shouldReturnErrorWhenOrderingOrderDirectionIsNull(){
        SearchReadersRequest request = SearchReadersRequest.builder().firstName("SSD").lastName("SCC").ordering(Ordering.builder().orderBy("FirstName").orderDirection(null).build()).paging(Paging.builder().pageSize(6).pageNumber(2).build()).build();
        List<ValidationError> errors =searchReadersRequestValidator.validate(request);
        Assertions.assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_14");
    }

    @Test
    public void shouldReturnErrorWhenOrderingOrderByNotContainFirstNameOrLastName(){
        SearchReadersRequest request = SearchReadersRequest.builder().firstName("SSD").lastName("SCC").ordering(Ordering.builder().orderBy("jjsns").orderDirection("JJjsa").build()).paging(Paging.builder().pageSize(6).pageNumber(2).build()).build();
        List<ValidationError> errors =searchReadersRequestValidator.validate(request);
        Assertions.assertEquals(errors.get(0).getErrorCode(), "ERROR_CODE_11");
    }
}
