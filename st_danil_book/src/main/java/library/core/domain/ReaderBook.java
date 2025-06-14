package library.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Entity
@Table(name = "reader_books")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ReaderBook {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reader_id" ,nullable = false)
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book_id" ,nullable = false)
    private Book  book;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "book_out_date",nullable = false)
    private Date book_out_date;

    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "book_return_date",nullable = false)
    private Date book_return_date;

    public ReaderBook(Reader reader, Book book, Date book_out_date, Date book_return_date) {
        this.reader = reader;
        this.book = book;
        this.book_out_date = book_out_date;
        this.book_return_date = book_return_date;
    }
}
