package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.StudentUser;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.StudentNotFoundException;
import mk.finki.diplomska.rabota.diplomska.repository.StudentRepository;
import mk.finki.diplomska.rabota.diplomska.services.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<StudentUser> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @Override
    public StudentUser findById(Long id) {
        return this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public StudentUser findByName(String name) {
        return this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
    }
}
