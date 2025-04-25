package com.learn.jpa.springBatch.Processor;

import com.learn.jpa.springBatch.entities.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonProcessor implements ItemProcessor<Person,Person> {
    private Logger logger = LoggerFactory.getLogger(PersonProcessor.class);

    @Override
    public Person process(Person item) throws Exception {
        Person newPerson = new Person();
        newPerson.setFirstName(item.getFirstName().toUpperCase());
        newPerson.setLastName(item.getLastName().toUpperCase());
        newPerson.setEmail(item.getEmail().toUpperCase());
        logger.info("Converting {} to {}", item, newPerson);
        return newPerson;
    }
}
