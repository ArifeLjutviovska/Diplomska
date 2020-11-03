package mk.finki.diplomska.rabota.diplomska.services;

import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanyUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<CompanyUser> getAllCompanies();
    CompanyUser findById(Long id);

    CompanyUser findByName(String name);

    CompanyUser updateCompany(Long id,CompanyUpdateRequest updateRequest);

    String deleteCompany(Long id);


}
