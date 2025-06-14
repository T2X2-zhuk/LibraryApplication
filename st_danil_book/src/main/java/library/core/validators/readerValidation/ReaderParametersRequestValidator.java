package library.core.validators.readerValidation;


import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Reader;
import library.core.validators.ValidationErrorFactory;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class ReaderParametersRequestValidator {

    @Autowired
    private ValidationErrorFactory errorFactory;
    @Autowired private JpaReaderRepository repository;

    public Optional<ValidationError> validateFirstName(String firstName){
        return (firstName == null || firstName.isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateLastName(String lastName){
        return (lastName == null || lastName.isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_8"))
                : Optional.empty();
    }

    public Optional<ValidationError> validatorReaderId(Long readerId){
        return (readerId == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_10"))
                : Optional.empty();
    }

    public Optional<ValidationError> isReaderInDataBase(String readerFirstName, String readerLastName){
        Reader reader = new Reader(readerFirstName,readerLastName);
        return (repository.findByFirstNameAndLastName
                (reader.getFirst_name(),reader.getLast_name()).isEmpty()
                ? Optional.of(errorFactory.buildError("ERROR_CODE_5"))
                : Optional.empty());
    }

    public Optional<ValidationError> validateBook_out_date(Date book_out_date) {
        return (book_out_date == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_3"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateBook_return_date(Date book_return_date) {
        return (book_return_date == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_4"))
                : Optional.empty();
    }
}
