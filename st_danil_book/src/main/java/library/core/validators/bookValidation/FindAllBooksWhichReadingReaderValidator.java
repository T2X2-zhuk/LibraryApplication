package library.core.validators.bookValidation;

import library.core.requests.AddReaderRequest;
import library.core.requests.FindAllBooksWhichReadingReaderRequest;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import library.core.validators.readerValidation.ReaderParametersRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FindAllBooksWhichReadingReaderValidator {

    @Autowired private ReaderParametersRequestValidator requestValidator;

    @Autowired private  AddReaderRequestValidator validator;


    public List<ValidationError> validate(FindAllBooksWhichReadingReaderRequest request){
        List<ValidationError> errors = new ArrayList<>();
        AddReaderRequest request1 = new AddReaderRequest(request.getReaderFirstName(),request.getReaderLastName());
        validator.validate(request1);
        if (!validator.validate(request1).isEmpty()){
            errors.addAll(validator.validate(request1));
        }
        requestValidator.isReaderInDataBase(request.getReaderFirstName(),request.getReaderLastName()).ifPresent(errors::add);
        return errors;
    }
}
