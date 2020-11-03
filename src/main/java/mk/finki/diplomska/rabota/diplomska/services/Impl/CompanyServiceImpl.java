package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.models.Job;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.CompanyNotFoundException;
import mk.finki.diplomska.rabota.diplomska.payload.request.CompanyUpdateRequest;
import mk.finki.diplomska.rabota.diplomska.repository.CompanyRepository;
import mk.finki.diplomska.rabota.diplomska.repository.JobRepository;
import mk.finki.diplomska.rabota.diplomska.services.CompanyService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository companyRepository;

    private final JobRepository jobRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
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
            if (!updateRequest.getCity().equals(user.getCity())) {
                user.setCity(updateRequest.getCity());
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
                       if(!this.jobRepository.existByTitle(j.getTitle(),user.getId())) {
                           try {
                               jobs.add(this.jobRepository.save(new Job(j.getTitle(), j.getDescription(), j.getRequiredSkills(), j.getWhatWeOffer(), j.getResponsibilities(), j.getPosition(), j.getLocation(), new SimpleDateFormat("dd/MM/yyyy").parse(j.getPublicationDateStart()), new SimpleDateFormat("dd/MM/yyyy").parse(j.getPublicationDateEnd()), j.getApplyEmail())));
                           } catch (ParseException e) {
                               e.printStackTrace();
                           }

                       }});

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


}
