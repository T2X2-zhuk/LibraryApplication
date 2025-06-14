package library.core.validators.bookValidation;

import library.core.database.jpa.JpaBookRepository;
import library.core.domain.Book;
import library.core.validators.ValidationErrorFactory;
import library.core.util.ValidationError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookParametersRequestValidator {

    @Autowired private ValidationErrorFactory errorFactory;

    @Autowired private JpaBookRepository repository;

    public Optional<ValidationError> validateTitle(String title) {
        return (title == null || title.isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_1"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateAuthor(String author) {
        return (author == null || author.isEmpty())
                ? Optional.of(errorFactory.buildError("ERROR_CODE_2"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateBookId(Long bookId) {
        return (bookId == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_9"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateIsPresentBookInDataBase(String title,String author){
        Book book = new Book(title,author);
        return (repository.findByTitleAndAuthor(book.getTitle(), book.getAuthor()).isEmpty()
                ? Optional.of(errorFactory.buildError("ERROR_CODE_6"))
                : Optional.empty());
    }

}
