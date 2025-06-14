package library.core.validators.readerValidation;

import library.core.requests.SearchReadersRequest;
import library.core.validators.orderingAndPagingValidate.OrderAndPageValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class SearchReadersRequestValidator {

    @Autowired
    private OrderAndPageValidator validator1;
    @Autowired
    private ReaderParametersRequestValidator validatorReaderParameters;

    public List<ValidationError> validate(SearchReadersRequest request){
        List<ValidationError> errors = new ArrayList<>();
        if (!request.isFirstNameProvided() && !request.isLastNameProvided()){
            validatorReaderParameters.validateFirstName(request.getFirstName()).ifPresent(errors::add);
            validatorReaderParameters.validateLastName(request.getFirstName()).ifPresent(errors::add);
        }
        if (request.getOrdering() != null) {
            validator1.validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validator1.validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validator1.validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validator1.validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if (request.getPaging() != null) {
            validator1.validatePageNumber(request.getPaging()).ifPresent(errors::add);
            validator1.validatePageSize(request.getPaging()).ifPresent(errors::add);
            validator1.validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
            validator1.validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }
}
