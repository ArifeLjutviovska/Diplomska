package mk.finki.diplomska.rabota.diplomska.services;

import mk.finki.diplomska.rabota.diplomska.models.StudentUser;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public List<StudentUser> getAllStudents();

   StudentUser findById(Long id);
  StudentUser findByName(String name);
}
