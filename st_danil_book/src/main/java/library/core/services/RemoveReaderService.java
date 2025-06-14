package library.core.services;

import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Reader;
import library.core.requests.RemoveReaderRequest;
import library.core.responses.RemoveReaderResponse;
import library.core.validators.readerValidation.RemoveReaderRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class RemoveReaderService {

    @Autowired private JpaReaderRepository repository;
    @Autowired private RemoveReaderRequestValidator validator;

    @Transactional
    public RemoveReaderResponse execute(RemoveReaderRequest request){
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveReaderResponse(errors);
        }
        Optional<Reader> reader = repository.findById(request.getReaderId());
        if (reader.isEmpty()){
            new RemoveReaderResponse(false);
        }else {
            repository.delete(reader.get());
        }
        return new RemoveReaderResponse(true);
    }
}
