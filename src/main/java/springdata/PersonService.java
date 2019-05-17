package springdata;

import entity.Person;

import java.util.List;

/**
 * This interface contains methods
 * for CRUD operations.
 */

public interface PersonService {
    void createPerson(Person person);

    void updatePerson(Person person);

    void deletePerson(Long id);

    Person findPersonById(Long id);

    List<Person> findAll();

    List<Person> findAllByBirthDay(int month, int day);

    Person findOneByEmail(String email);
}
