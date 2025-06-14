package library.core.services;
import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Reader;
import library.core.requests.Ordering;
import library.core.requests.Paging;
import library.core.requests.SearchReadersRequest;
import library.core.responses.SearchReaderResponse;
import library.core.validators.readerValidation.SearchReadersRequestValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchReadersService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired private JpaReaderRepository repository;
    @Autowired private SearchReadersRequestValidator validator;

    @Transactional
    public SearchReaderResponse execute(SearchReadersRequest request) {
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchReaderResponse(errors, null);
        }

        List<Reader> readers = search(request);
        readers = order(readers, request.getOrdering());
        readers = paging(readers, request.getPaging());

        return new SearchReaderResponse(null,readers );
    }
    private List<Reader> search(SearchReadersRequest request) {
        List<Reader> readers = new ArrayList<>();
        if (request.isFirstNameProvided() && !request.isLastNameProvided()) {
            readers = repository.findByFirstName(request.getFirstName());
        }
        if (!request.isFirstNameProvided() && request.isLastNameProvided()) {
            readers = repository.findByLastName(request.getLastName());
        }
        if (request.isFirstNameProvided() && request.isLastNameProvided()) {
            readers = repository.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        }
        return readers;
    }
    private List<Reader> order(List<Reader> readers, Ordering ordering) {
        if (orderingEnabled && (ordering != null)) {
            Comparator<Reader> comparator = ordering.getOrderBy().equals("title")
                    ? Comparator.comparing(Reader::getFirst_name)
                    : Comparator.comparing(Reader::getLast_name);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return readers.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return readers;
        }
    }

    private List<Reader> paging(List<Reader> readers, Paging paging) {
        if (pagingEnabled && (paging != null)) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return readers.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return readers;
        }
    }
}
