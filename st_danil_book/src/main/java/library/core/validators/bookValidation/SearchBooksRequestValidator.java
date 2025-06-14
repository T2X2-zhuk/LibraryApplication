package library.core.validators.bookValidation;


import library.core.requests.SearchBooksRequest;
import library.core.validators.orderingAndPagingValidate.OrderAndPageValidator;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchBooksRequestValidator {

	@Autowired
	private BookParametersRequestValidator requestValidator;
	@Autowired private OrderAndPageValidator validator;

	public List<ValidationError> validate(SearchBooksRequest request) {
		List<ValidationError> errors = new ArrayList<>();
		if (!request.isTitleProvided() && !request.isAuthorProvided()){
			requestValidator.validateTitle(request.getTitle()).ifPresent(errors::add);
			requestValidator.validateAuthor(request.getAuthor()).ifPresent(errors::add);
		}
		if (request.getOrdering() != null) {
			validator.validateOrderBy2(request.getOrdering()).ifPresent(errors::add);
			validator.validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
			validator.validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
			validator.validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
		}
		if (request.getPaging() != null) {
			validator.validatePageNumber(request.getPaging()).ifPresent(errors::add);
			validator.validatePageSize(request.getPaging()).ifPresent(errors::add);
			validator.validateMandatoryPageNumber(request.getPaging()).ifPresent(errors::add);
			validator.validateMandatoryPageSize(request.getPaging()).ifPresent(errors::add);
		}

		return errors;
	}
}