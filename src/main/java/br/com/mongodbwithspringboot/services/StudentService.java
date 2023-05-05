package br.com.mongodbwithspringboot.services;

import br.com.mongodbwithspringboot.entities.Student;
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

    public Page<Student> findAll(String pageString, String sizeString,String sortString) {
        Sort.Direction direction = (sortString.equalsIgnoreCase("desc")) ?
                                    Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "name");

        int page = Integer.parseInt(pageString);
        int size = Integer.parseInt(sizeString);

        Pageable pageable = PageRequest.of(page, size, sort);

        return studentRepository.findAll(pageable);

    }

    public Student findById(String id) {
        if (id != null)
            return studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No students with id " + id + " were found"));

        throw new RuntimeException("Invalid fields");
    }

    public Student insert(Student student) {
        if (student != null)
            return studentRepository.insert(student);
        throw new RuntimeException("Student should not be null");
    }

    public Student update(Student student) {
        if (student != null && student.getId() != null)
            return studentRepository.insert(student);
        throw new RuntimeException("Invalid datas");
    }

    public String delete(String id) {
        if (id != null) {
            studentRepository.deleteById(id);
            return "Student with id " + id + " has been deleted";
        }
        throw new RuntimeException("Id should not be null");
    }

}
