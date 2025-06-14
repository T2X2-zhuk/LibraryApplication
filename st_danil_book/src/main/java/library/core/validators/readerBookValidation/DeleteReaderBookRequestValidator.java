package library.core.validators.readerBookValidation;

import library.core.requests.AddBookRequest;
import library.core.requests.AddReaderRequest;
import library.core.requests.DeleteReaderBookRequest;
import library.core.validators.bookValidation.AddBookRequestValidator;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteReaderBookRequestValidator {

    @Autowired private AddBookRequestValidator bookRequestValidator;
    @Autowired private AddReaderRequestValidator readerRequestValidator;

    public List<ValidationError> validate(DeleteReaderBookRequest request){
        AddBookRequest request1 = new AddBookRequest(request.getTitle(),request.getAuthor());
        AddReaderRequest request2 = new AddReaderRequest(request.getFirstNameReader(),request.getLastNameReader());
        List<ValidationError> errors = new ArrayList<>();
        if (!bookRequestValidator.validate(request1).isEmpty()){
            errors.addAll(bookRequestValidator.validate(request1));
        }
        if (!readerRequestValidator.validate(request2).isEmpty()){
            errors.addAll(readerRequestValidator.validate(request2));
        }
        return errors;
    }

}
