package mk.finki.diplomska.rabota.diplomska.controllers;


import mk.finki.diplomska.rabota.diplomska.models.*;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.CityNotFoundException;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.InvalidEmailException;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanySignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.LoginRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentSignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.response.JwtResponse;
import mk.finki.diplomska.rabota.diplomska.payload.response.MessageResponse;
import mk.finki.diplomska.rabota.diplomska.repository.CitiesRepository;
import mk.finki.diplomska.rabota.diplomska.repository.CompanyRepository;
import mk.finki.diplomska.rabota.diplomska.repository.RoleRepository;
import mk.finki.diplomska.rabota.diplomska.repository.StudentRepository;
import mk.finki.diplomska.rabota.diplomska.security.jwt.JwtUtils;
import mk.finki.diplomska.rabota.diplomska.security.services.UserDetailsImpl;
import mk.finki.diplomska.rabota.diplomska.services.DBFileStorageService;
import mk.finki.diplomska.rabota.diplomska.services.Impl.CompanyServiceImpl;
import mk.finki.diplomska.rabota.diplomska.services.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    CompanyServiceImpl companyService;

    @Autowired
    StudentServiceImpl studentService;


    @GetMapping("/userType")
    public String getUserType(@RequestParam String email){
       return this.studentService.getUserType(email);
    }

    @PostMapping("/studentSignin")
    public ResponseEntity<?> authenticateStudent(@Valid @RequestBody LoginRequest loginRequest) {

      return this.studentService.loginStudent(loginRequest);
    }

    @PostMapping("/studentSignup")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentSignUpRequest signUpRequest) {
      return this.studentService.registerStudent(signUpRequest);
    }

    @PostMapping("/companySignin")
    public ResponseEntity<?> authenticateCompany(@Valid @RequestBody LoginRequest loginRequest) {

       return this.companyService.loginCompany(loginRequest);
    }

    @PostMapping("/companySignup")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody CompanySignUpRequest signUpRequest) {
        return this.companyService.registerCompany(signUpRequest);
    }

    @GetMapping("/authName")
    public String getLoginUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String auth_name = auth.getName();
        return auth_name;

    }
}
