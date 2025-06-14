package library.core.validators;

import library.core.util.ErrorCodeUtil;
import library.core.util.Placeholder;
import library.core.util.ValidationError;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public class ValidationErrorFactory {

    @Autowired private ErrorCodeUtil errorCodeUtil;


    public ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }

    public ValidationError buildError1(String errorCode, List<Placeholder> placeholders) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode, placeholders);
        return new ValidationError(errorCode, errorDescription);
    }

}
