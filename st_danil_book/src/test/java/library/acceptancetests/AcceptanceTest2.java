package library.acceptancetests;
import library.core.requests.AddBookRequest;
import library.core.requests.Ordering;
import library.core.requests.Paging;
import library.core.requests.SearchBooksRequest;
import library.core.responses.SearchBooksResponse;
import library.core.services.AddBookService;
import library.core.services.SearchBooksService;
import library.DatabaseCleaner;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/schema.sql"})
public class AcceptanceTest2 {

	@Autowired
	private AddBookService addBookService;
	@Autowired private SearchBooksService searchBooksService;
	@Autowired private DatabaseCleaner databaseCleaner;

	@Before
	public void setup() {
		databaseCleaner.clean();
	}

	@Test
	public void searchBooks() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		SearchBooksRequest request3 = new SearchBooksRequest("Title", null);
		SearchBooksResponse response = searchBooksService.execute(request3);

		assertEquals(response.getBooks().size(), 2);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
		assertEquals(response.getBooks().get(1).getTitle(), "Title");
		assertEquals(response.getBooks().get(1).getAuthor(), "Author2");
	}

	@Test
	public void searchBooksOrderingDescending() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		Ordering ordering = new Ordering("author", "DESCENDING");
		SearchBooksRequest request3 = new SearchBooksRequest("Title", null, ordering);
		SearchBooksResponse response = searchBooksService.execute(request3);

		Assertions.assertEquals(response.getBooks().size(), 2);
		Assertions.assertEquals(response.getBooks().get(0).getTitle(), "Title");
		Assertions.assertEquals(response.getBooks().get(0).getAuthor(), "Author2");
		Assertions.assertEquals(response.getBooks().get(1).getTitle(), "Title");
		Assertions.assertEquals(response.getBooks().get(1).getAuthor(), "Author1");
	}

	@Test
	public void searchBooksOrderingAscending() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		Ordering ordering = new Ordering("author", "ASCENDING");
		SearchBooksRequest request3 = new SearchBooksRequest("Title", null, ordering);
		SearchBooksResponse response = searchBooksService.execute(request3);

		assertEquals(response.getBooks().size(), 2);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
		assertEquals(response.getBooks().get(1).getTitle(), "Title");
		assertEquals(response.getBooks().get(1).getAuthor(), "Author2");
	}

	@Test
	public void searchBooksOrderingPaging() {
		AddBookRequest request1 = new AddBookRequest("Title", "Author1");
		addBookService.execute(request1);

		AddBookRequest request2 = new AddBookRequest("Title", "Author2");
		addBookService.execute(request2);

		Ordering ordering = new Ordering("author", "ASCENDING");
		Paging paging = new Paging(1, 1);
		SearchBooksRequest request3 = new SearchBooksRequest("Title", null, ordering, paging);
		SearchBooksResponse response = searchBooksService.execute(request3);

		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author1");
	}


}


