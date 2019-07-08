package com.robertdue.social.repos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.robertdue.social.model.Friendship;
import com.robertdue.social.model.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FriendshipRepositoryTest {

    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IFriendshipRepository friendshipRepository;

    @Test
    public void givenFriendshipRepository_whenSaveAndRetreiveEntities_thenOK() {
        Person personA = personRepository.save(new Person("personA"));
        Person personB = personRepository.save(new Person("personB"));

        Friendship friendship = friendshipRepository.save(new Friendship(personA, personB));
        List<Friendship> foundFriendships = friendshipRepository.findFriendshipsByPerson(personA);

        assertEquals(foundFriendships.size(), 1);
        assertTrue(foundFriendships.contains(friendship));
    }
}