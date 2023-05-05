package br.com.mongodbwithspringboot.controllers;

import br.com.mongodbwithspringboot.entities.Student;
import br.com.mongodbwithspringboot.exceptions.BadRequestException;
import br.com.mongodbwithspringboot.exceptions.InvalidDataException;
import br.com.mongodbwithspringboot.exceptions.StudentNotFoundException;
import br.com.mongodbwithspringboot.services.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Student>> findAll(
           @RequestParam(required = false, name = "page", defaultValue = "1") String pageString,
           @RequestParam(required = false, name = "size", defaultValue = "10") String sizeString,
           @RequestParam(required = false, name = "sort", defaultValue = "ASC") String sortString
        ) throws BadRequestException {

        Page<Student> students = studentService.findAll(pageString, sizeString, sortString);
        return ResponseEntity.status(HttpStatus.OK).body(students);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findById(@PathVariable String id)
            throws InvalidDataException,
            StudentNotFoundException,
            BadRequestException {

        Student student = studentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(student);

    }

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody Student student)
            throws InvalidDataException, BadRequestException {

        Student createdStudent = studentService.insert(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent.getId());

    }

    @PutMapping("/update")
    public ResponseEntity<Student> update(@RequestBody Student student)
            throws InvalidDataException, BadRequestException {

        Student updated = studentService.update(student);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws InvalidDataException, BadRequestException {

        studentService.delete(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Student with id " + id + " has been deleted with success");

    }

}
