package br.com.mongodbwithspringboot.services;

import br.com.mongodbwithspringboot.entities.Student;
import br.com.mongodbwithspringboot.exceptions.BadRequestException;
import br.com.mongodbwithspringboot.exceptions.InvalidDataException;
import br.com.mongodbwithspringboot.exceptions.StudentNotFoundException;
import br.com.mongodbwithspringboot.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<Student> findAll(String pageString, String sizeString,String sortString) throws BadRequestException {
        try {
            Sort.Direction direction = (sortString.equalsIgnoreCase("desc")) ?
                    Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = Sort.by(direction, "name");

            int page = Integer.parseInt(pageString);
            int size = Integer.parseInt(sizeString);

            Pageable pageable = PageRequest.of(page, size, sort);

            return studentRepository.findAll(pageable);
        }catch (Exception e) {
            throw new BadRequestException("The findAll operation could not be performed: " + e.getMessage());
        }
    }

    public Student findById(String id) throws StudentNotFoundException, InvalidDataException, BadRequestException {
        try {

            if (id == null) throw new InvalidDataException("Id should not be null");

            return studentRepository.findById(id)
                    .orElseThrow(() -> new StudentNotFoundException("No students with id " + id + " were found"));

        }catch (Exception e) {
            throw new BadRequestException("The findById operation could not be performed: " + e.getMessage());
        }
    }

    public Student insert(Student student) throws InvalidDataException, BadRequestException {
        try {

            if (student == null) throw new InvalidDataException("Student should not be null");

            return studentRepository.insert(student);

        }catch (Exception e) {
            throw new BadRequestException("The insert operation could not be performed: " + e.getMessage());
        }
    }

    public Student update(Student student) throws InvalidDataException, BadRequestException {
        try {

            if (student == null || student.getId() == null)
                throw new InvalidDataException("Id or student should not be null");

            return studentRepository.save(student);

        }catch (Exception e) {
            throw new BadRequestException("The update operation could not be performed: " + e.getMessage());
        }
    }

    public void delete(String id) throws BadRequestException, InvalidDataException {
        try {

            if (id == null) throw new InvalidDataException("Id should not be null");

            studentRepository.deleteById(id);

        }catch (Exception e) {
            throw new BadRequestException("The delete operation could not be performed: " + e.getMessage());
        }
    }

}