package com.robertdue.social.repos;

import java.util.List;

import com.robertdue.social.model.Person;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IPersonRepository extends PagingAndSortingRepository<Person, Long> {
    List<Person> findByName(@Param("name") String name);

    Person findOneByName(@Param("name") String name);
}