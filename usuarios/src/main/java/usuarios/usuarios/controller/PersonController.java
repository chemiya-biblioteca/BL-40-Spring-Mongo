package usuarios.usuarios.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import usuarios.usuarios.collection.Person;
import usuarios.usuarios.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;//le llamo al servicio para que haga operaciones que sera el que llama al repository que es el que accede a la bd

    @PostMapping
    public String save(@RequestBody Person person) {
        return personService.save(person);//guardo
    }

    @GetMapping
    public List<Person> getPersonStartWith(@RequestParam("name") String name) {
        return personService.getPersonStartWith(name);//?me llega y busco que su nombre comienza por
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        personService.delete(id);//elimino por id que me llega
    }

    @GetMapping("/age")
    public List<Person> getByPersonAge(@RequestParam Integer minAge,
                                       @RequestParam Integer maxAge) {
        return personService.getByPersonAge(minAge,maxAge);//me llegan ? y busco en el rango
    }

  

    @GetMapping("/oldestPerson")//busca la persona mas antigua
    public List<Document> getOldestPerson() {
        return personService.getOldestPersonByCity();
    }

    @GetMapping("/populationByCity")//busca cuantos tienen ciudad valladolid, cuantos zamora
    public List<Document> getPopulationByCity() {
        return personService.getPopulationByCity();
    }
}
