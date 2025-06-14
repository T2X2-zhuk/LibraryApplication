package library.core.services;

import library.core.database.jpa.JpaReaderRepository;
import library.core.domain.Reader;
import library.core.requests.GetAllReadersRequest;
import library.core.responses.GetAllReadersResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllReadersServiceTest extends LibraryServiceValidationTest {

    @Mock private JpaReaderRepository repository;

    @InjectMocks private GetAllReadersService service;

    @Test
    public void shouldGetReadersFromDb() {
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("Title", "Author"));
        Mockito.when(repository.getAllReaders()).thenReturn(readers);

        GetAllReadersRequest request = new GetAllReadersRequest();
        GetAllReadersResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getReaderList().size(), 1);
        assertEquals(response.getReaderList().get(0).getFirst_name(), "Title");
        assertEquals(response.getReaderList().get(0).getLast_name(), "Author");
    }
}
