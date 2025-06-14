package library.acceptancetests;
import library.core.requests.AddReaderRequest;
import library.core.requests.GetAllReadersRequest;
import library.core.responses.GetAllReadersResponse;
import library.core.services.AddReaderService;
import library.core.services.GetAllReadersService;
import library.DatabaseCleaner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql"})
public class AcceptanceTest3 {

    @Autowired
    private AddReaderService service;

    @Autowired private GetAllReadersService readersService;

    @Autowired private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldReturnCorrectBookList() {
        AddReaderRequest request = new AddReaderRequest("Kags" , "kjjhds");
        service.execute(request);

        AddReaderRequest request2 = new AddReaderRequest("Kagsss" , "kjjhdshg");
        service.execute(request2);

        GetAllReadersResponse response = readersService.execute(new GetAllReadersRequest());
        Assert.assertEquals(response.getReaderList().size(),2);
    }
}

