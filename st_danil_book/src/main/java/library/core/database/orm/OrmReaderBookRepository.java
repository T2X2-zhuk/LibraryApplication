package library.core.database.orm;
import library.core.domain.Book;
import library.core.domain.Reader;
import library.core.domain.ReaderBook;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//@Component
//@Transactional
public class OrmReaderBookRepository implements ReaderBookRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(ReaderBook readerBook){
        sessionFactory.getCurrentSession().save(readerBook);
    }

    @Override
    public void delete(ReaderBook readerBook) {
        Query query = sessionFactory.getCurrentSession().createQuery("Delete ReaderBook WHERE id = : id");
        query.setParameter("id",readerBook.getId());
        query.executeUpdate();
    }

    @Override
    public List<ReaderBook> getByIdReaderAndIdBook(Book book, Reader reader){
        Query<ReaderBook> query = sessionFactory.getCurrentSession().createQuery
                ("SELECT rb From ReaderBook rb WHERE reader_id = : reader_id AND book_id = : book_id", ReaderBook.class);
        query.setParameter("reader_id",reader.getId());
        query.setParameter("book_id",book.getId());

       return query.getResultList();
    }

    @Override
    public List<ReaderBook> getAllReaderBooks(Reader reader){
        Query<ReaderBook> query = sessionFactory.getCurrentSession().
                createQuery("SELECT rb FROM ReaderBook rb where rb.reader = :reader", ReaderBook.class);
        query.setParameter("reader",reader);
        return query.getResultList();
    }

    @Override
    public List<ReaderBook> getAllReaderBooksSimple() {
        Query query = sessionFactory.getCurrentSession().createQuery("SELECT rb FROM ReaderBook rb", ReaderBook.class);
        return  query.getResultList();
    }

    @Override
    public List<Book> getAllBooksId(Reader reader){
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT book FROM ReaderBook where reader_id = :reader_id", Book.class);
        query.setParameter("reader_id",reader.getId());
        return query.getResultList();
    }



}
