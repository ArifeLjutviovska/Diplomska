package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.*;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.CityNotFoundException;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.InvalidEmailException;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.LanguageNotFoundException;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.StudentNotFoundException;
import mk.finki.diplomska.rabota.diplomska.payload.request.LoginRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentSignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.StudentUpdateModel;
import mk.finki.diplomska.rabota.diplomska.payload.response.JwtResponse;
import mk.finki.diplomska.rabota.diplomska.payload.response.MessageResponse;
import mk.finki.diplomska.rabota.diplomska.repository.*;
import mk.finki.diplomska.rabota.diplomska.security.jwt.JwtUtils;
import mk.finki.diplomska.rabota.diplomska.security.services.UserDetailsImpl;
import mk.finki.diplomska.rabota.diplomska.services.DBFileStorageService;
import mk.finki.diplomska.rabota.diplomska.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SkillsRepository skillsRepository;
    private final SubjectRepository subjectRepository;
    private final JobExperienceRepository jobExperienceRepository;
    private final CategoryRepository categoryRepository;
    private final LanguagesRepository languagesRepository;
    @Autowired
    private DBFileStorageService dbFileStorageService;
    @Autowired
    DBFileRepository fileRepository;

    @Autowired
    CompanyServiceImpl companyService;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BranchRepository branchRepository;


    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CitiesRepository citiesRepository;

    public StudentServiceImpl(StudentRepository studentRepository, SkillsRepository skillsRepository, SubjectRepository subjectRepository, JobExperienceRepository jobExperienceRepository, CategoryRepository categoryRepository, LanguagesRepository languagesRepository) {
        this.studentRepository = studentRepository;
        this.skillsRepository = skillsRepository;
        this.subjectRepository = subjectRepository;
        this.jobExperienceRepository = jobExperienceRepository;
        this.categoryRepository = categoryRepository;
        this.languagesRepository = languagesRepository;
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

    @Override
    public StudentUser updateStudent(Long id, StudentUpdateModel model) {
        StudentUser user = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        if (model.getStudentName() != null) {
            if (!user.getName().equals(model.getStudentName())) {
                user.setName(model.getStudentName());
            }
        }
        if (model.getEmail() != null) {
            if (!user.getEmail().equals(model.getEmail())) {
                user.setEmail(model.getEmail());
            }
        }
        if (model.getContactEmail() != null) {
            if (!user.getContactEmail().equals(model.getContactEmail())) {
                user.setContactEmail(model.getContactEmail());
            }
        }
        if (model.getContactPhone() != null) {
            if (!user.getContactPhone().equals(model.getContactPhone())) {
                user.setContactPhone(model.getContactPhone());
            }
        }
        if (model.getAddress() != null) {
            if (!user.getAddress().equals(model.getAddress())) {
                user.setAddress(model.getAddress());
            }
        }
        if (model.getSummary() != null) {

            user.setSummary(model.getSummary());

        }
        if (model.getCity() != null) {
            if (!user.getCity().getName().equals(model.getCity())) {
                if (this.citiesRepository.existsByName(model.getCity())) {
                    user.setCity(this.citiesRepository.findByName(model.getCity()).orElseThrow(CityNotFoundException::new));
                } else {
                    user.setCity(this.citiesRepository.save(new City(model.getCity())));
                }
            }
        }

        if (model.getPassword() != null) {
            if (!user.getPassword().equals(model.getPassword())) {
                user.setPassword(model.getPassword());
            }
        }
        if (model.getYearOfStudies() != 0) {
            if (user.getYearOfStudies() != model.getYearOfStudies()) {
                user.setYearOfStudies(model.getYearOfStudies());
            }
        }
        if (model.getImg() != null) {
            if (user.getImg() != model.getImg()) {
                user.setImg(model.getImg());
            }
        }
        if (model.getCV() != null) {
            if (user.getCV() != model.getCV()) {
                user.setCV(model.getCV());
            }
        }
        if (model.getPol() != null) {
            if (!user.getGender().toString().equals(model.getPol())) {
                Gender p;
                if (model.getPol().equals(Gender.Female.toString())) {
                    p = Gender.Female;
                } else {
                    p = Gender.Male;
                }
                user.setGender(p);
            }
        }

        if (model.getBranch() != null) {
            if (user.getBranch() != model.getBranch()) {
                if (this.branchRepository.existsByName(model.getBranch().getName())) {
                    user.setBranch(this.branchRepository.findByName(model.getBranch().getName()));
                } else {
                    user.setBranch(this.branchRepository.save(new Branch(model.getBranch().getName())));
                }

            }
        }
        if (model.getSkills() != null && model.getSkills().size() != 0) {

            model.getSkills().forEach(skill -> {
                List<Skill> skills = new ArrayList<>();
                boolean exist = user.getSkills().stream().anyMatch(s -> s.getName().equals(skill.getName()));
                if (!exist) {
                    if (this.skillsRepository.existsByName(skill.getName())) {
                        skills.add(this.skillsRepository.findByName(skill.getName()));
                    } else {
                        List<Category> categories = new ArrayList<>();
                        skill.getCategories().forEach(c -> {
                            if (this.categoryRepository.existsByName(c.getName())) {
                                categories.add(this.categoryRepository.findByName(c.getName()));
                            } else {
                                categories.add(this.categoryRepository.save(new Category(c.getName())));
                            }
                        });
                        skills.add(this.skillsRepository.save(new Skill(skill.getName(), categories)));
                    }
                    user.getSkills().addAll(skills);
                }


            });

        }

        if (model.getSubjects() != null && model.getSubjects().size() != 0) {
            List<Subject> subjects = new ArrayList<>();
            model.getSubjects().forEach(subject -> {

                boolean exist = this.subjectRepository.ExistByNameStudent(subject.getName(), id);
                if (!exist) {
                    if (this.subjectRepository.existsByName(subject.getName())) {
                        subjects.add(this.subjectRepository.findByName(subject.getName()));
                    } else {
                        List<Skill> subjectSkills = new ArrayList<>();
                        subject.getSkills().forEach(skill -> {
                            if (this.skillsRepository.existsByName(skill.getName())) {
                                subjectSkills.add(this.skillsRepository.findByName(skill.getName()));
                            } else {
                                List<Category> categories = new ArrayList<>();
                                skill.getCategories().forEach(c -> {
                                    if (this.categoryRepository.existsByName(c.getName())) {
                                        categories.add(this.categoryRepository.findByName(c.getName()));
                                    } else {
                                        categories.add(this.categoryRepository.save(new Category(c.getName())));
                                    }
                                });
                                subjectSkills.add(this.skillsRepository.save(new Skill(skill.getName(), categories)));
                            }
                        });
                        subjects.add(this.subjectRepository.save(new Subject(subject.getName(), subject.getDescription(), subjectSkills)));
                    }
                    user.getSubjects().addAll(subjects);
                    subjects.forEach(s -> {
                        s.getSkills().forEach(sk -> {
                            if (!user.getSkills().contains(sk)) {
                                user.getSkills().add(sk);
                            }
                        });

                    });
                }
            });


        }
        if (model.getLanguages() != null && model.getLanguages().size() != 0) {
            model.getLanguages().forEach(l -> {
                if (this.languagesRepository.existsByName(l.getName())) {
                    user.getLanguages().add(this.languagesRepository.findByName(l.getName()).orElseThrow(LanguageNotFoundException::new));
                } else {
                    user.getLanguages().add(this.languagesRepository.save(new Language(l.getName())));
                }
            });
        }

        if (model.getExperience() != null && model.getExperience().size() != 0) {
            List<JobExperience> experiences = new ArrayList<JobExperience>();
            model.getExperience().forEach(e -> {
                boolean doesExist = user.getExperience().stream().anyMatch(ex -> ex.getCompanyName().equals(e.getCompanyName()) && ex.getCategory().equals(e.getCategory()) && ex.getDateStart().equals(e.getDateStart()));
                if (!doesExist) {
                    if (this.categoryRepository.existsByName(e.getCategory().getName())) {
                        try {
                            experiences.add(this.jobExperienceRepository.save(new JobExperience(e.getCompanyName(), this.categoryRepository.findByName(e.getCategory().getName()), e.getDescription(), new SimpleDateFormat("dd/MM/yyyy").parse(e.getDateStart()), new SimpleDateFormat("dd/MM/yyyy").parse(e.getDateEnd()))));
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        Category cat = new Category(e.getCategory().getName());
                        this.categoryRepository.save(cat);
                        try {
                            experiences.add(this.jobExperienceRepository.save(new JobExperience(e.getCompanyName(), cat, e.getDescription(), new SimpleDateFormat("dd/MM/yyyy").parse(e.getDateStart()), new SimpleDateFormat("dd/MM/yyyy").parse(e.getDateEnd()))));
                        } catch (ParseException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            });
            user.getExperience().addAll(experiences);
        }


        user.setRoles(user.getRoles());
        return this.studentRepository.save(user);

    }

    @Override
    public String deleteStudent(Long id) {
        StudentUser user = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        this.studentRepository.deleteById(id);
        return "Student user deleted with name " + user.getName();
    }

    @Override
    public ResponseEntity<?> loginStudent(LoginRequest loginRequest) {
        StudentUser user = this.studentRepository.findByEmail(loginRequest.getEmail()).orElseThrow(InvalidEmailException::new);

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

    @Override
    public ResponseEntity<?> registerStudent(StudentSignUpRequest signUpRequest) {
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
        Gender p;
        if (signUpRequest.getPol().equals(Gender.Female.toString())) {
            p = Gender.Female;
        } else {
            p = Gender.Male;
        }
        user.setGender(p);
        if (this.citiesRepository.existsByName(signUpRequest.getCity())) {
            user.setCity(this.citiesRepository.findByName(signUpRequest.getCity()).orElseThrow(CityNotFoundException::new));
        } else {
            user.setCity(this.citiesRepository.save(new City(signUpRequest.getCity())));
        }
        user.setAddress(signUpRequest.getAddress());
        user.setContactEmail(signUpRequest.getContactEmail());
        user.setContactPhone(signUpRequest.getContactPhone());
        studentRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
    }

    @Override
    public String getUserType(String email) {
        String res = "";
        StudentUser user = this.studentRepository.findByEmail(email).orElse(null);
        if (user != null) {
            res = UserType.Student.toString();
        } else {
            res = UserType.Company.toString();
        }
        return res;
    }

    @Override
    public DBFile getStudentImg(Long id) {
        StudentUser user = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return user.getImg();
    }

    @Override
    public boolean StudentHasImg(Long id) {
        StudentUser user = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return user.getImg() != null;

    }

    @Override
    public boolean StudentHasImg(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getImg() != null;
    }

    @Override
    public DBFile getStudentImg(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getImg();
    }

    @Override
    public List<String> getStudentSkills(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getSkills().stream().map(Skill::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getSubjectNames(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getSubjects().stream().map(Subject::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getLanguages(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getLanguages().stream().map(Language::getName).collect(Collectors.toList());
    }

    @Override
    public String getStudentCity(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getCity().getName();
    }

    @Override
    public String getBranch(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getBranch().getName();
    }

    @Override
    public List<JobExperience> getExperiences(String name) {
        StudentUser user=this.studentRepository.findByName(name).orElseThrow(StudentNotFoundException::new);
        return user.getExperience();
    }
}
