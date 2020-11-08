package mk.finki.diplomska.rabota.diplomska.services;

import mk.finki.diplomska.rabota.diplomska.models.StudentUser;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanySignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.LoginRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentSignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentUpdateModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public List<StudentUser> getAllStudents();

   StudentUser findById(Long id);
  StudentUser findByName(String name);

  StudentUser updateStudent(Long id, StudentUpdateModel model);

  String deleteStudent(Long id);

    ResponseEntity<?> loginStudent(LoginRequest loginRequest);

    ResponseEntity<?> registerStudent(StudentSignUpRequest signUpRequest);
    String getUserType(String email);
}
