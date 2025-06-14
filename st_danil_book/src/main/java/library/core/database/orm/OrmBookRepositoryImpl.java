package library.core.database.orm;

import library.core.database.orm.BookRepository;
import library.core.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

//@Component
//@Transactional
public class OrmBookRepositoryImpl  {

    /*@Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "delete Book where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Book> getAllBooks() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Book b", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> findByTitle(String title) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Book b where title = :title");
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthor(String author) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Book b where author = :author");
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        Query query = sessionFactory.getCurrentSession().createQuery(
                "select b FROM Book b where title = : title AND author = :author");
        query.setParameter("title", title);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public boolean isPresentBookInDataBase(Book book) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT b FROM Book b WHERE" +
                        " title = :title AND author = :author");
        query.setParameter("title",book.getTitle());
        query.setParameter("author",book.getAuthor());

        if (!query.getResultList().isEmpty()){
            return true;
        }
        return false;
    }*/
}
