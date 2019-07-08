package com.robertdue.social.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
public @Data class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    Person personA;

    @ManyToOne
    Person personB;

    public Friendship() {
    }

    public Friendship(Person personA, Person personB) {
        this.personA = personA;
        this.personB = personB;
    }
}