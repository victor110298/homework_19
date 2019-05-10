package springdata;

import entity.Person;

import java.util.List;

/**
 * This interface contains methods
 * for CRUD operations.
 */

public interface PersonService {
    void createUser(Person person);

    void updateUser(Person person);

    void deleteUser(Long id);

    Person findUserById(Long id);

    List<Person> findAll();

    List<Person> findAllByBirthDay(int month, int day);

    Person findOneByEmail(String email);
}
