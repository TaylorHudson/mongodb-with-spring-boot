package br.com.mongodbwithspringboot.repositories;

import br.com.mongodbwithspringboot.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

}
