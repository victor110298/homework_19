
import config.AppTestConfiguration;
import entity.Person;
import lombok.NoArgsConstructor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springdata.PersonService;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppTestConfiguration.class)
public class SpringDataApplicationTest {

    @Autowired
    private PersonService service;

    @Before
    public void setUp() {
       Person person = new Person()
                .setName("Petro")
                .setAge(25)
                .setEmail("petroit@mail.com")
                .setBirthday(LocalDate.of(1998, 10, 23));
        service.createPerson(person);
    }

    @After
    public void drop()
    {
        service.deletePerson(service.findOneByEmail("petroit@mail.com").getId());
    }

    @Test
    public void createStudentTest() {
        Person person = new Person()
                .setName("Leo")
                .setAge(21)
                .setEmail("leokiller@mail.com")
                .setBirthday(LocalDate.of(1997, 11, 20));
        service.createPerson(person);
        assertTrue(service.findAll().contains(person));
    }

    @Test
    public void shouldCreateUser() {
        Person created = new Person()
                .setName("petro")
                .setAge(34)
                .setEmail("petro11@email.com")
                .setBirthday(LocalDate.of(1991, 4, 2));
        service.createPerson(created);
        assertTrue(service.findAll().contains(created));
    }

    @Test
    public void shouldUpdateUser() {
        Person person = service.findOneByEmail("petro11@email.com");
        person.setName("TEST");
        service.updatePerson(person);
        Person updated = service.findPersonById(person.getId());
        assertEquals(person.getName(), updated.getName());
    }

    @Test
    public void shouldReturnUserById() {
        Person person = service.findOneByEmail("petro11@email.com");
        Long id = person.getId();
        Person target = service.findPersonById(id);
        assertEquals(person, target);
    }

    @Test
    public void shouldDeleteUser() {
        Person created = new Person()
                .setName("anna")
                .setAge(23)
                .setEmail("ann1@email.com")
                .setBirthday(LocalDate.of(2001, 4, 2));
        service.createPerson(created);
        service.deletePerson(created.getId());
        assertFalse(service.findAll().contains(created));
    }
}
