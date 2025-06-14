package library.core.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="books")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Book {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title",nullable = false)
	private String title;

	@Column(name = "author",nullable = false)
	private String author;

	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

}
