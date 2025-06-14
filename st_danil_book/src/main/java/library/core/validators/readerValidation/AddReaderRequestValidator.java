package library.core.validators.readerValidation;

import library.core.requests.AddReaderRequest;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddReaderRequestValidator {

    @Autowired private ReaderParametersRequestValidator requestValidator;

    public List<ValidationError> validate(AddReaderRequest addReaderRequest){
        List<ValidationError> errors = new ArrayList<>();
        requestValidator.validateFirstName(addReaderRequest.getFirstName()).ifPresent(errors::add);
        requestValidator.validateLastName(addReaderRequest.getLastName()).ifPresent(errors::add);
        return errors;
    }
}
