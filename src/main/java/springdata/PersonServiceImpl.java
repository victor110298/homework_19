package springdata;

import entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonRepository;

import java.util.List;

/**
 * This class implements PersonService
 * and realise this methods
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Override
    public void createPerson(Person person) {
        repository.save(person);
    }

    @Override
    public void updatePerson(Person person) {
        repository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Person findPersonById(Long id) {
        if(repository.findById(id).isPresent()) {
            return repository.findById(id).get();
        }
        else throw new IllegalArgumentException("Illegal id");
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Person> findAllByBirthDay(int month, int day) {
        return repository.findByMatchMonthAndMatchDay(month, day);
    }

    @Override
    public Person findOneByEmail(String email) {
        return repository.findOneByEmail(email);
    }
}