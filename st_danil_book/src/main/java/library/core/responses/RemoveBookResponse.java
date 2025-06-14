package library.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.util.ValidationError;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RemoveBookResponse extends CoreResponse {

	private boolean bookRemoved;

	public RemoveBookResponse(List<ValidationError> errors) {
		super(errors);
	}

	public RemoveBookResponse(boolean bookRemoved) {
		this.bookRemoved = bookRemoved;
	}

}