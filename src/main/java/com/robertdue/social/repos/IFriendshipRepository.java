package com.robertdue.social.repos;

import java.util.List;

import com.robertdue.social.model.Friendship;
import com.robertdue.social.model.Person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IFriendshipRepository extends PagingAndSortingRepository<Friendship, Long> {
    @Query(value = "select f from Friendship f where personA = :person OR personB = :person")
    public List<Friendship> findFriendshipsByPerson(@Param("person") Person person);
}