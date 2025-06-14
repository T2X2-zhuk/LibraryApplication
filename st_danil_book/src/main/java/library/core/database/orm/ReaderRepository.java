package library.core.database.orm;


import library.core.domain.Reader;

import java.util.List;

public interface ReaderRepository {

    void save(Reader reader);

    boolean deleteById(Long id);

    List<Reader> getAllReaders();

    List<Reader> findByFirstName(String FirstName);

    List<Reader> findByLastName(String LastName);

    List<Reader> findByFirstNameAndLastName(String FirstName, String LastName);

    List<Reader> findByFirstNameAndLastNameOneReader(String FirstName, String LastName);

    boolean isWhetherReaderWithFirstNameAndLastNameInDataBase(Reader reader);
}
