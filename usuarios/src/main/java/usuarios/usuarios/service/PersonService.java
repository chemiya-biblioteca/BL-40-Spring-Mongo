package usuarios.usuarios.service;

import java.util.List;

import org.bson.Document;

import usuarios.usuarios.collection.Person;

public interface PersonService {//interfaz con los metodos
    String save(Person person);

    List<Person> getPersonStartWith(String name);

    void delete(String id);

    List<Person> getByPersonAge(Integer minAge, Integer maxAge);

    List<Document> getOldestPersonByCity();

    List<Document> getPopulationByCity();

  
}
