package mk.finki.diplomska.rabota.diplomska.controllers;


import mk.finki.diplomska.rabota.diplomska.models.*;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.InvalidEmailException;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanySignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.LoginRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentSignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.response.JwtResponse;
import mk.finki.diplomska.rabota.diplomska.payload.response.MessageResponse;
import mk.finki.diplomska.rabota.diplomska.repository.CompanyRepository;
import mk.finki.diplomska.rabota.diplomska.repository.RoleRepository;
import mk.finki.diplomska.rabota.diplomska.repository.StudentRepository;
import mk.finki.diplomska.rabota.diplomska.security.jwt.JwtUtils;
import mk.finki.diplomska.rabota.diplomska.security.services.UserDetailsImpl;
import mk.finki.diplomska.rabota.diplomska.services.DBFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private DBFileStorageService dbFileStorageService;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/userType")
    public String getUserType(@RequestParam String email){
        String res="";
        StudentUser user=this.studentRepository.findByEmail(email).orElse(null);
        if(user!=null){
            res=UserType.Student.toString();
        }else{
           res=  UserType.Company.toString();
        }
        return res;
    }

    @PostMapping("/studentSignin")
    public ResponseEntity<?> authenticateStudent(@Valid @RequestBody LoginRequest loginRequest) {

        StudentUser user=this.studentRepository.findByEmail(loginRequest.getEmail()).orElseThrow(InvalidEmailException::new);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/studentSignup")
    public ResponseEntity<?> registerStudent(@Valid @RequestBody StudentSignUpRequest signUpRequest) {
        if (companyRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already taken!"));
        }

        if (companyRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        if (studentRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already taken!"));
        }

        if (studentRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        StudentUser user = new StudentUser(signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        studentRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
    }

    @PostMapping("/companySignin")
    public ResponseEntity<?> authenticateCompany(@Valid @RequestBody LoginRequest loginRequest) {

        CompanyUser user=this.companyRepository.findByEmail(loginRequest.getEmail()).orElseThrow(InvalidEmailException::new);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/companySignup")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody CompanySignUpRequest signUpRequest) {
        if (studentRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already taken!"));
        }

        if (studentRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        if (companyRepository.existsByName(signUpRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Name is already taken!"));
        }

        if (companyRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        CompanyUser user = new CompanyUser(signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        user.setAddress(signUpRequest.getAddress());
        user.setCity(signUpRequest.getCity());
        user.setContactName(signUpRequest.getContactName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());



            DBFile logo=this.dbFileStorageService.getFile(signUpRequest.getLogo().getId());
            user.setLogo(logo);



        companyRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Company registered successfully!"));
    }
}
