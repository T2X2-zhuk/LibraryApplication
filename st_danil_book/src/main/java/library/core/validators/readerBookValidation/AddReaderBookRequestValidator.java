package library.core.validators.readerBookValidation;
import library.core.requests.AddBookRequest;
import library.core.requests.AddReaderBookRequest;
import library.core.requests.AddReaderRequest;
import library.core.validators.bookValidation.BookParametersRequestValidator;
import library.core.validators.readerValidation.AddReaderRequestValidator;
import library.core.validators.bookValidation.AddBookRequestValidator;
import library.core.validators.readerValidation.ReaderParametersRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddReaderBookRequestValidator {

    @Autowired private BookParametersRequestValidator validatorBook;
    @Autowired private ReaderParametersRequestValidator readerValidator;
    @Autowired private AddBookRequestValidator validator1;
    @Autowired private AddReaderRequestValidator validator;

    public List<ValidationError> validate(AddReaderBookRequest request) {
        AddBookRequest addBookRequest = new AddBookRequest(request.getTitle(),request.getAuthor());
        AddReaderRequest addReaderRequest = new AddReaderRequest(request.getFirstNameReader(),request.getLastNameReader());
        List<ValidationError> errors = new ArrayList<>();

        if (!validator1.validate(addBookRequest).isEmpty()){
            errors.addAll(validator1.validate(addBookRequest));
        }
        if (!validator.validate(addReaderRequest).isEmpty()){
            errors.addAll(validator.validate(addReaderRequest));
        }

        readerValidator.validateBook_out_date(request.getBook_out_date()).ifPresent(errors :: add);
        readerValidator.validateBook_return_date(request.getBook_return_date()).ifPresent(errors :: add);

        validatorBook.validateIsPresentBookInDataBase(request.getTitle(),request.getAuthor()).ifPresent(errors :: add);
        readerValidator.isReaderInDataBase(request.getFirstNameReader(),request.getLastNameReader()).ifPresent(errors :: add);

        return errors;
    }
}
