package library.acceptancetests;

import library.core.requests.AddBookRequest;
import library.core.requests.GetAllBooksRequest;
import library.core.responses.GetAllBooksResponse;
import library.core.services.AddBookService;
import library.core.services.GetAllBooksService;
import library.DatabaseCleaner;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql"})
public class AcceptanceTest1 {

	@Autowired
	private AddBookService addBookService;
	@Autowired private GetAllBooksService getAllBooksService;
	@Autowired private DatabaseCleaner databaseCleaner;

	@BeforeEach
	public void setup() {
		databaseCleaner.clean();
	}

	@Test
	public void shouldReturnCorrectBookList() {
		AddBookRequest addBookRequest1 = new AddBookRequest("TitleA", "AuthorA");
		addBookService.execute(addBookRequest1);

		AddBookRequest addBookRequest2 = new AddBookRequest("TitleA", "AuthorA");
		addBookService.execute(addBookRequest2);

		GetAllBooksResponse response = getAllBooksService.execute(new GetAllBooksRequest());
		assertEquals(response.getBooks().size(), 2);
	}
}


