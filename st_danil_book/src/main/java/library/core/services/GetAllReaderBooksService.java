package library.core.services;


import library.core.database.jpa.JpaReaderBookRepository;
import library.core.domain.ReaderBook;
import library.core.responses.GetAllReaderBooksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class GetAllReaderBooksService {

    @Autowired private JpaReaderBookRepository repository;

    @Transactional

    public GetAllReaderBooksResponse execute(){
        List<ReaderBook> readerBooks = repository.getAllReaderBooksSimple();
        return new GetAllReaderBooksResponse(readerBooks);
    }
}
