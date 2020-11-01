package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.CompanyNotFoundException;
import mk.finki.diplomska.rabota.diplomska.repository.CompanyRepository;
import mk.finki.diplomska.rabota.diplomska.services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<CompanyUser> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public CompanyUser findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    @Override
    public CompanyUser findByName(String name) {
        return this.companyRepository.findByName(name).orElseThrow(CompanyNotFoundException::new);
    }
}
