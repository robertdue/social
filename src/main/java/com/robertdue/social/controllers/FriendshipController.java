package com.robertdue.social.controllers;

import java.util.List;
import java.util.Optional;

import com.robertdue.social.model.Friendship;
import com.robertdue.social.model.Person;
import com.robertdue.social.repos.IFriendshipRepository;
import com.robertdue.social.repos.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendshipController {
    @Autowired
    IFriendshipRepository friendshipRepository;

    @Autowired
    IPersonRepository personRepository;

    @GetMapping(value = "/friendships")
    Iterable<Friendship> showAllFriendships(Pageable pageable) {
        return friendshipRepository.findAll(pageable);
    }

    @GetMapping(value = "/friendships/{id}")
    ResponseEntity<Friendship> showFriendship(@PathVariable("id") long id) {
        return friendshipRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/friendships/{id}")
    ResponseEntity<Friendship> removeFriendship(@PathVariable("id") long id) {
        friendshipRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/friendships")
    ResponseEntity<Friendship> addFriendship(@PathVariable("personA") long personA,
            @RequestParam("personB") long personB) {
        Optional<Person> person = personRepository.findById(personA);
        Optional<Person> newFriend = personRepository.findById(personB);
        if (person.isPresent() && newFriend.isPresent() && !person.equals(newFriend)) {
            List<Friendship> foundFriendships = friendshipRepository.findFriendshipsByPersons(person.get(),
                    newFriend.get());
            if (foundFriendships.size() == 0) {
                Friendship newFriendship;
                newFriendship = new Friendship(person.get(), newFriend.get());
                newFriendship = friendshipRepository.save(newFriendship);
                return new ResponseEntity<Friendship>(newFriendship, HttpStatus.ACCEPTED);
            }
        }
        return ResponseEntity.unprocessableEntity().build();
    }

}
