package mk.finki.diplomska.rabota.diplomska.services;

import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.models.DBFile;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanySignUpRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanyUpdateRequest;
import mk.finki.diplomska.rabota.diplomska.payload.request.LoginRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<CompanyUser> getAllCompanies();

    DBFile getCompanyLogo(String companyName);
    CompanyUser findById(Long id);

    CompanyUser findByName(String name);

    CompanyUser updateCompany(Long id,CompanyUpdateRequest updateRequest);

    String deleteCompany(Long id);

    ResponseEntity<?> loginCompany(LoginRequest loginRequest);

    ResponseEntity<?> registerCompany(CompanySignUpRequest signUpRequest);


}
