package mk.finki.diplomska.rabota.diplomska.security.services;



import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.models.User;
import mk.finki.diplomska.rabota.diplomska.repository.CompanyRepository;
import mk.finki.diplomska.rabota.diplomska.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if(studentRepository.existsByName(username)){
            user=studentRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with name: " + username));
        }
        else{
            user=companyRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with name: " + username));
        }

        return UserDetailsImpl.build(user);
    }

}