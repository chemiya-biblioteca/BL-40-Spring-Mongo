package usuarios.usuarios.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import usuarios.usuarios.collection.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

    List<Person> findByFirstNameStartsWith(String name);//que comience por

    //List<Person> findByAgeBetween(Integer min, Integer max);

    @Query(value = "{ 'age' : { $gt : ?0, $lt : ?1}}",
           fields = "{addresses:  0}")//busca con los parametros, greate y lower, sin la direccion
    List<Person> findPersonByAgeBetween(Integer min, Integer max);
}
