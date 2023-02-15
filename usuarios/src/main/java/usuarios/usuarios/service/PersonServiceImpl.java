package usuarios.usuarios.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;


import usuarios.usuarios.collection.Person;
import usuarios.usuarios.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonRepository personRepository;//llamo repositorio

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public String save(Person person) {
        return personRepository.save(person).getPersonId();//lo guarda y coge id
    }

    @Override
    public List<Person> getPersonStartWith(String name) {
        return personRepository.findByFirstNameStartsWith(name);//busqueda
    }

    @Override
    public void delete(String id) {
        personRepository.deleteById(id);//elimina
    }

    @Override
    public List<Person> getByPersonAge(Integer minAge, Integer maxAge) {
        return personRepository.findPersonByAgeBetween(minAge,maxAge);//busca en rango el repositorio
    }

    

    @Override
    public List<Document> getOldestPersonByCity() {//busca la mas vieja
        UnwindOperation unwindOperation
                = Aggregation.unwind("addresses");//deconstruye array y crea instacia con cada elemento
        SortOperation sortOperation
                = Aggregation.sort(Sort.Direction.DESC,"age");//ordena en desc por columna año
        GroupOperation groupOperation
                = Aggregation.group("addresses.city")//agrupa por misma ciudad
                .first(Aggregation.ROOT)
                .as("oldestPerson");//nombre que le da

        Aggregation aggregation
                = Aggregation.newAggregation(unwindOperation,sortOperation,groupOperation);//junto todo

        List<Document> person
                = mongoTemplate.aggregate(aggregation, Person.class,Document.class).getMappedResults();
        return person;//recoge los datos
    }

    @Override
    public List<Document> getPopulationByCity() {//Busca habitanbtes

        UnwindOperation unwindOperation
                = Aggregation.unwind("addresses");//separa por ciudades, una instacia por cada ciudad que tenga una persona
        GroupOperation groupOperation
                = Aggregation.group("addresses.city")//agrupa por ciudad y cuenta
                .count().as("popCount");
        SortOperation sortOperation
                = Aggregation.sort(Sort.Direction.DESC, "popCount");//ordena por la cuenta

        ProjectionOperation projectionOperation
                = Aggregation.project()//para solo seleccionar las columnas que quiera
                .andExpression("_id").as("city")//selecciona esta columna
                .andExpression("popCount").as("count")
                .andExclude("_id");

        Aggregation aggregation//junta todo
                = Aggregation.newAggregation(unwindOperation,groupOperation,sortOperation,projectionOperation);

        List<Document> documents//devuelve conjunto de resultado
                = mongoTemplate.aggregate(aggregation,
                Person.class,
                Document.class).getMappedResults();
        return  documents;
    }


}
