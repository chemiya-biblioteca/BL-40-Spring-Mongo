package pruebamongo.mongo.controller;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import pruebamongo.mongo.collection.Person;
import pruebamongo.mongo.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;//servcicio

    @PostMapping
    public String save(@RequestBody Person person) {
        return personService.save(person);//guardo
    }

    @GetMapping
    public List<Person> getPersonStartWith(@RequestParam("name") String name) {
        return personService.getPersonStartWith(name);//?me llega y busco
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personService.delete(id);//elimino por id que me llega
    }

    @GetMapping("/age")
    public List<Person> getByPersonAge(@RequestParam Integer minAge,
                                       @RequestParam Integer maxAge) {
        return personService.getByPersonAge(minAge,maxAge);//me llegan ? y busco
    }

  

    @GetMapping("/oldestPerson")//busca la mas antigua
    public List<Document> getOldestPerson() {
        return personService.getOldestPersonByCity();
    }

    @GetMapping("/populationByCity")//busca gente
    public List<Document> getPopulationByCity() {
        return personService.getPopulationByCity();
    }
}
