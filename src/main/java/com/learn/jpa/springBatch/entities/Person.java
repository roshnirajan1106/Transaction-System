package com.learn.jpa.springBatch.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Person {
    private String firstName;
    private String lastName;
    private String email;
}
