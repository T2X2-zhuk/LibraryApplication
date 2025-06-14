package library.core.validators.readerValidation;
import library.core.requests.RemoveReaderRequest;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class RemoveReaderRequestValidator {

    @Autowired private ReaderParametersRequestValidator requestValidator;

    public List<ValidationError> validate(RemoveReaderRequest request){
        List<ValidationError> errors = new ArrayList<>();
        requestValidator.validatorReaderId(request.getReaderId()).ifPresent(errors::add);
        return errors;
    }

}
