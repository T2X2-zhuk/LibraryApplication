package library.core.validators.orderingAndPagingValidate;

import library.core.requests.Ordering;
import library.core.requests.Paging;
import library.core.validators.ValidationErrorFactory;
import library.core.util.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderAndPageValidator {

    @Autowired
    private ValidationErrorFactory errorFactory;

    public Optional<ValidationError> validateOrderBy(Ordering ordering){
        return (ordering.getOrderBy() != null && !(ordering.getOrderBy().equalsIgnoreCase("FirstName")
                || ordering.getOrderBy().equalsIgnoreCase("LastName")))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_11"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateOrderDirection(Ordering ordering){
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().equals("ASCENDING")
                || ordering.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_12"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_13"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_14"))
                : Optional.empty();
    }
    public Optional<ValidationError> validatePageNumber(Paging paging) {
        return (paging.getPageNumber() != null
                && paging.getPageNumber() <= 0)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_15"))
                : Optional.empty();
    }

    public Optional<ValidationError> validatePageSize(Paging paging) {
        return (paging.getPageSize() != null
                && paging.getPageSize() <= 0)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_16"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateMandatoryPageNumber(Paging paging) {
        return (paging.getPageNumber() == null && paging.getPageSize() != null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_17"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateMandatoryPageSize(Paging paging) {
        return (paging.getPageSize() == null && paging.getPageNumber() != null)
                ? Optional.of(errorFactory.buildError("ERROR_CODE_18"))
                : Optional.empty();
    }

    public Optional<ValidationError> validateOrderBy2(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().equalsIgnoreCase("author") || ordering.getOrderBy().equalsIgnoreCase("title")))
                ? Optional.of(errorFactory.buildError("ERROR_CODE_19"))
                : Optional.empty();
    }
}
