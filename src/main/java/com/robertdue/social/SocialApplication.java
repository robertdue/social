package com.robertdue.social;

import java.util.Arrays;

import com.robertdue.social.model.Friendship;
import com.robertdue.social.model.Person;
import com.robertdue.social.repos.IFriendshipRepository;
import com.robertdue.social.repos.IPersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialApplication implements CommandLineRunner {

	@Autowired
	IPersonRepository personRepository;

	@Autowired
	IFriendshipRepository friendshipRepository;

	public static void main(String[] args) {
		SpringApplication.run(SocialApplication.class, args);
	}

	@Override
	public void run(String... params) throws Exception {
		/* create some demo data */
		System.out.println("create some persons");
		String[] persons = new String[] { "Philipp", "Robert", "Sarah", "Martin" };
		Arrays.stream(persons).forEach((String element) -> {
			personRepository.save(new Person(element));
		});

		System.out.println("create a friendship");
		Person philipp = personRepository.findOneByName("Philipp");
		Person robert = personRepository.findOneByName("Robert");
		friendshipRepository.save(new Friendship(philipp, robert));
	}

}
