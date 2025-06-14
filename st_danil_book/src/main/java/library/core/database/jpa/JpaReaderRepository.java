package library.core.database.jpa;

import library.core.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaReaderRepository extends JpaRepository<Reader, Long> {

    Optional<Reader> findById(Long id);

    @Query(value = "SELECT rd FROM Reader rd")
    List<Reader> getAllReaders();


    @Query(value = "SELECT rd FROM Reader rd WHERE first_name = :firstName")
    List<Reader> findByFirstName(@Param("firstName") String first_name);

    @Query(value = "SELECT rd FROM Reader rd WHERE last_name = :lastName")
    List<Reader> findByLastName(@Param("lastName") String last_name);

    @Query(value = "SELECT rd FROM Reader rd WHERE last_name = :lastName AND first_name = :firstName")
    List<Reader> findByFirstNameAndLastName(@Param("firstName") String first_name, @Param("lastName") String last_name);

}
