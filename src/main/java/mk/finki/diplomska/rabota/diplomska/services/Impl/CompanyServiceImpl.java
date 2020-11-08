package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.*;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.CityNotFoundException;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.CompanyNotFoundException;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.InvalidEmailException;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanySignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanyUpdateRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.LoginRequest;
import mk.finki.diplomska.rabota.diplomska.payload.response.JwtResponse;
import mk.finki.diplomska.rabota.diplomska.payload.response.MessageResponse;
import mk.finki.diplomska.rabota.diplomska.repository.*;
import mk.finki.diplomska.rabota.diplomska.security.jwt.JwtUtils;
import mk.finki.diplomska.rabota.diplomska.security.services.UserDetailsImpl;
import mk.finki.diplomska.rabota.diplomska.services.CompanyService;
import mk.finki.diplomska.rabota.diplomska.services.DBFileStorageService;
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
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CitiesRepository citiesRepository;
    private final SkillsRepository skillsRepository;
    private final JobRepository jobRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    private DBFileStorageService dbFileStorageService;


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentRepository studentRepository;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public CompanyServiceImpl(CompanyRepository companyRepository, CitiesRepository citiesRepository, SkillsRepository skillsRepository, JobRepository jobRepository, CategoryRepository categoryRepository) {
        this.companyRepository = companyRepository;
        this.citiesRepository = citiesRepository;
        this.skillsRepository = skillsRepository;
        this.jobRepository = jobRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CompanyUser> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public DBFile getCompanyLogo(String companyName) {
        CompanyUser user=this.companyRepository.findByName(companyName).orElseThrow(CompanyNotFoundException::new);

        return user.getLogo();
    }

    @Override
    public CompanyUser findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public CompanyUser findByName(String name) {
        return this.companyRepository.findByName(name).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public CompanyUser updateCompany(Long id,CompanyUpdateRequest updateRequest) {
        CompanyUser user=this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        if(updateRequest.getPhoneNumber()!=null) {
        if(!updateRequest.getPhoneNumber().equals(user.getPhoneNumber())){

                user.setPhoneNumber(updateRequest.getPhoneNumber());
            }
        }
        if(updateRequest.getLogo()!=null){
          if(updateRequest.getLogo()!=user.getLogo()) {
            user.setLogo(updateRequest.getLogo());
          }
        }
        if(updateRequest.getContactName()!=null) {
            if (!updateRequest.getContactName().equals(user.getContactName())) {
                user.setContactName(updateRequest.getContactName());
            }
        }
        if(updateRequest.getCity()!=null) {
            if (updateRequest.getCity()!=user.getCity()) {
                if(this.citiesRepository.existsByName(updateRequest.getCity().getName())){
                    user.setCity(this.citiesRepository.findByName(updateRequest.getCity().getName()).orElseThrow(CityNotFoundException::new));
                }else{
                    user.setCity(this.citiesRepository.save(new City(updateRequest.getCity().getName())));
                }

            }
        }
        if(updateRequest.getAddress()!=null) {
            if (!updateRequest.getAddress().equals(user.getAddress())) {
                user.setAddress(updateRequest.getAddress());
            }
        }
        if(updateRequest.getCompanyName()!=null) {
            if (!updateRequest.getCompanyName().equals(user.getName())) {
                user.setName(updateRequest.getCompanyName());
            }
        }
        if(updateRequest.getEmail()!=null) {
            if (!updateRequest.getEmail().equals(user.getEmail())) {
                user.setEmail(updateRequest.getEmail());
            }
        }
        if(updateRequest.getPassword()!=null) {
            if (!updateRequest.getPassword().equals(user.getPassword())) {
                user.setPassword(updateRequest.getPassword());
            }
        }
        if(updateRequest.getJobs()!=null&&updateRequest.getJobs().size()!=0) {
            List<Job> jobs=new ArrayList<Job>();
                   updateRequest.getJobs().forEach(j-> {
                       CompanyUser companyUser=this.companyRepository.findByName(j.getCompanyName()).orElseThrow(CompanyNotFoundException::new);
                         List<Skill> techs=new ArrayList<>();
                           City city;
                           if(this.citiesRepository.existsByName(j.getCity().getName())){
                               city=this.citiesRepository.findByName(j.getCity().getName()).orElseThrow(CityNotFoundException::new);
                           }else{
                               city=this.citiesRepository.save(new City(j.getCity().getName()));
                           }

                           List<Category> skillCat=new ArrayList<>();
                           j.getTechnologies().forEach(t->{
                               if(this.skillsRepository.existsByName(t.getName())){
                                   techs.add(this.skillsRepository.findByName(t.getName()));
                               }else{
                                   t.getCategoryList().forEach(cat->{
                                       if(this.categoryRepository.existsByName(cat.getName())){
                                           skillCat.add(this.categoryRepository.findByName(cat.getName()));
                                       }else{
                                           skillCat.add(this.categoryRepository.save(new Category(cat.getName())));
                                       }
                                   });
                                   techs.add(this.skillsRepository.save(new Skill(t.getName(),skillCat)));
                               }
                           });
                           if (this.categoryRepository.existsByName(j.getCategory().getName())) {
                               try {
                                   jobs.add(this.jobRepository.save(new Job(j.getTitle(), j.getDescription(), j.getRequiredSkills(), j.getWhatWeOffer(), j.getResponsibilities(), new SimpleDateFormat("dd/MM/yyyy").parse(j.getPublicationDateStart()), new SimpleDateFormat("dd/MM/yyyy").parse(j.getPublicationDateEnd()), j.getApplyEmail(),this.categoryRepository.findByName(j.getCategory().getName()),user.getName(), city,j.getJobType(),techs)));
                               } catch (ParseException e) {
                                   e.printStackTrace();
                               }
                           }else{
                               Category cat=new Category(j.getCategory().getName());
                               this.categoryRepository.save(cat);
                               try {
                                   jobs.add(this.jobRepository.save(new Job(j.getTitle(), j.getDescription(), j.getRequiredSkills(), j.getWhatWeOffer(), j.getResponsibilities(),  new SimpleDateFormat("dd/MM/yyyy").parse(j.getPublicationDateStart()), new SimpleDateFormat("dd/MM/yyyy").parse(j.getPublicationDateEnd()), j.getApplyEmail(),cat,user.getName(),city,j.getJobType(),techs)));
                               } catch (ParseException e) {
                                   e.printStackTrace();
                               }

                           }


                       });

                       user.getJobs().addAll(jobs);

        }
        user.setRoles(user.getRoles());
        return this.companyRepository.save(user);
    }

    @Override
    public String deleteCompany(Long id) {
        CompanyUser user=this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        List<Job> jobs=user.getJobs();
        this.companyRepository.delete(user);
        jobs.stream().forEach(this.jobRepository::delete);

        return "Company with name: "+user.getName()+" and this companies job offers are deleted";
    }

    @Override
    public ResponseEntity<?> loginCompany(LoginRequest loginRequest) {
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

    @Override
    public ResponseEntity<?> registerCompany(CompanySignUpRequest signUpRequest) {
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
        if(this.citiesRepository.existsByName(signUpRequest.getCity().getName())){
            user.setCity(this.citiesRepository.findByName(signUpRequest.getCity().getName()).orElseThrow(CityNotFoundException::new));
        }else{
            user.setCity(this.citiesRepository.save(new City(signUpRequest.getCity().getName())));
        }

        user.setContactName(signUpRequest.getContactName());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());



        DBFile logo=this.dbFileStorageService.getFile(signUpRequest.getLogo().getId());
        user.setLogo(logo);



        companyRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Company registered successfully!"));
    }


}
