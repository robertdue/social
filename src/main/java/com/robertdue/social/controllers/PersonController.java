package com.robertdue.social.controllers;

import java.util.Optional;

import com.robertdue.social.model.Friendship;
import com.robertdue.social.model.Person;
import com.robertdue.social.repos.IFriendshipRepository;
import com.robertdue.social.repos.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IFriendshipRepository friendshipRepository;

    /* show all persons */
    @GetMapping(value = "/persons")
    Iterable<Person> showAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    /* show person details */
    @GetMapping(value = "/persons/{id}")
    ResponseEntity<Person> showPerson(@PathVariable("id") long id) {
        return personRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /* create person */
    @PostMapping(value = "/persons")
    Person addPerson(@PathVariable("id") long id, @RequestBody Person person) {
        return personRepository.save(person);
    }

    /* update person details by id */
    @PutMapping(value = "/persons/{id}")
    ResponseEntity<Person> editPerson(@PathVariable("id") long id, @RequestBody Person person) {
        return personRepository.findById(id).map(record -> {
            record.setName(person.getName());
            Person updatedPerson = personRepository.save(record);
            return ResponseEntity.ok().body(updatedPerson);
        }).orElse(ResponseEntity.notFound().build());
    }

    /* get friendships for this person */
    @GetMapping(value = "/persons/{id}/friendships")
    Iterable<Friendship> showFriends(@PathVariable("id") long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return friendshipRepository.findFriendshipsByPerson(person.get());
        }
        return null;
    }
}
