package de.codecentric.springbootsample;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by andreas on 28.02.17.
 */
public interface PersonRepository extends CrudRepository<DBPerson, Integer> {

}
