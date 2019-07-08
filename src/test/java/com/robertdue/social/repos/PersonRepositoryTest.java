package com.robertdue.social.repos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.robertdue.social.model.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private IPersonRepository personRepository;

    @Test
    public void givenPersonRepository_whenSaveAndRetreiveEntity_thenOK() {
        Person person = personRepository.save(new Person("test"));
        Person foundPerson = personRepository.findOneByName("test");
        List<Person> foundPersonList = personRepository.findByName("test");

        assertEquals(foundPersonList.size(), 1);
        assertNotNull(foundPerson);
        assertEquals(person, foundPerson);
    }
}