package library.core.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import library.core.domain.Reader;
import lombok.Getter;

import java.util.List;
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllReadersResponse extends CoreResponse{

    private List<Reader> readerList;

    public GetAllReadersResponse(List<Reader> readerList) {
        this.readerList = readerList;
    }

}
