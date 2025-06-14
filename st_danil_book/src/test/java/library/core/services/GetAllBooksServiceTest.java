package library.core.services;

import library.core.database.jpa.JpaBookRepository;
import library.core.domain.Book;
import library.core.requests.GetAllBooksRequest;
import library.core.responses.GetAllBooksResponse;

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
public class GetAllBooksServiceTest extends LibraryServiceValidationTest {

	@Mock private JpaBookRepository database;
	@InjectMocks private GetAllBooksService service;

	@Test
	public void shouldGetBooksFromDb() {
		List<Book> books = new ArrayList<>();
		books.add(new Book("Title", "Author"));
		Mockito.when(database.getAllBooks()).thenReturn(books);

		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = service.execute(request);
		assertFalse(response.hasErrors());
		assertEquals(response.getBooks().size(), 1);
		assertEquals(response.getBooks().get(0).getTitle(), "Title");
		assertEquals(response.getBooks().get(0).getAuthor(), "Author");
	}

}