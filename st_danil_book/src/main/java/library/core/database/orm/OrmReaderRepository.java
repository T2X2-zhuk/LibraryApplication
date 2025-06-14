package library.core.database.orm;

import library.core.domain.Reader;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
//@Component
//@Transactional
public class OrmReaderRepository implements ReaderRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(Reader reader) {
        sessionFactory.getCurrentSession().save(reader);
    }

    @Override
    public boolean deleteById(Long id) {
        Query query = sessionFactory.
                getCurrentSession().createQuery("DELETE Reader WHERE id = :id");
        query.setParameter("id",id);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Reader> getAllReaders() {
        return sessionFactory.getCurrentSession().
                createQuery("SELECT rd FROM Reader rd", Reader.class).getResultList();

    }

    @Override
    public List<Reader> findByFirstName(String FirstName) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT rd FROM Reader rd WHERE first_Name = :first_name");
        query.setParameter("first_name",FirstName);
        return query.getResultList();
    }

    @Override
    public List<Reader> findByLastName(String LastName) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT rd FROM Reader rd WHERE last_Name = :last_name");
        query.setParameter("last_name",LastName);
        return query.getResultList();
    }

    @Override
    public List<Reader> findByFirstNameAndLastName(String FirstName, String LastName) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT rd FROM Reader rd WHERE" +
                        " first_name = :first_name AND last_name = :last_name");
        query.setParameter("first_name",FirstName);
        query.setParameter("last_name",LastName);
        return query.getResultList();
    }

    @Override
    public List<Reader> findByFirstNameAndLastNameOneReader(String FirstName, String LastName) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT rd FROM Reader rd WHERE" +
                        " first_name = :first_name AND last_name = :last_name");
        query.setParameter("first_name",FirstName);
        query.setParameter("last_name",LastName);
        return query.getResultList();
    }

    @Override
    public boolean isWhetherReaderWithFirstNameAndLastNameInDataBase(Reader reader) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("SELECT distinct r FROM Reader r WHERE" +
                        " first_name = :first_name AND last_name = :last_name");
        query.setParameter("first_name",reader.getFirst_name());
        query.setParameter("last_name",reader.getLast_name());

        if (!query.getResultList().isEmpty()){
            return true;
        }
        return false;
    }
}
