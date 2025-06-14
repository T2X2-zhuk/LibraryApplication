package library.core.services;

import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Reader;
import library.core.requests.GetAllReadersRequest;
import library.core.responses.GetAllReadersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetAllReadersService {

    @Autowired
    private JpaReaderRepository database;

    @Transactional
    public GetAllReadersResponse execute(GetAllReadersRequest request) {
        List<Reader> readers = database.getAllReaders();
        return new GetAllReadersResponse(readers);
    }
}
