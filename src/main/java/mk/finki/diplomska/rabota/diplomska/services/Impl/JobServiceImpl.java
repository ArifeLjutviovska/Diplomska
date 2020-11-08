package mk.finki.diplomska.rabota.diplomska.services.Impl;

import mk.finki.diplomska.rabota.diplomska.models.CompanyUser;
import mk.finki.diplomska.rabota.diplomska.models.Job;
import mk.finki.diplomska.rabota.diplomska.models.Skill;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.CompanyNotFoundException;
import mk.finki.diplomska.rabota.diplomska.models.exceptions.JobNotFoundException;
import mk.finki.diplomska.rabota.diplomska.payload.request.JobUpdateRequest;
import mk.finki.diplomska.rabota.diplomska.repository.CompanyRepository;
import mk.finki.diplomska.rabota.diplomska.repository.JobRepository;
import mk.finki.diplomska.rabota.diplomska.services.JobsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class JobServiceImpl implements JobsService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Job> getAllJobs() {

        return this.jobRepository.findAll();
    }

    @Override
    public Job getJobById(Long id) {
        return this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
    }

    @Override
    public String deleteJob(Long id) {
        Job j=this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        CompanyUser c= this.companyRepository.findByName(j.getCompany()).orElseThrow(CompanyNotFoundException::new);
        c.getJobs().remove(j);
         this.jobRepository.deleteById(id);
         return "Job deleted";
    }

    @Override
    public Job jobUpdate(Long id,JobUpdateRequest model) {
        return null;
    }

    @Override
    public String getJobsCity(Long id) {

        Job job=this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        return job.getCity().getName();
    }

    @Override
    public String categoryName(Long id) {
        Job job=this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        return job.getCategory().getName();
    }

    @Override
    public Set<String> getResponsibilities(Long id) {
        Job job=this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        return job.getResponsibilities();
    }

    @Override
    public Set<String> gerRequiredSkills(Long id) {
        Job job=this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        return job.getRequiredSkills();
    }

    @Override
    public List<Skill> getTechnologies(Long id) {
        Job job=this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        return job.getTechnologies();
    }

    @Override
    public Set<String> getWhatWeOffer(Long id) {
        Job job=this.jobRepository.findById(id).orElseThrow(JobNotFoundException::new);
        return job.getWhatWeOffer();
    }
}
