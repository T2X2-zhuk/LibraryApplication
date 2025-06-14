package library.core.services;

import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Reader;
import library.core.requests.AddReaderRequest;
import library.core.responses.AddReaderResponse;
import library.core.validators.readerValidation.AddReaderRequestValidator;

import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AddReaderService {

    @Autowired private JpaReaderRepository repository;

    @Autowired private AddReaderRequestValidator validator;

    @Transactional
    public AddReaderResponse execute(AddReaderRequest request){

        List<ValidationError> errors = validator.validate(request);

        if (!errors.isEmpty()){
            return new AddReaderResponse(errors);
        }
        Reader reader = new Reader(request.getFirstName(), request.getLastName());
        repository.save(reader);

        return new AddReaderResponse(reader);
    }
}
