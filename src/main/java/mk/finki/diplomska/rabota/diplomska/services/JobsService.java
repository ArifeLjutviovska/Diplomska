package mk.finki.diplomska.rabota.diplomska.services;

import mk.finki.diplomska.rabota.diplomska.models.Job;
import mk.finki.diplomska.rabota.diplomska.models.Skill;
import mk.finki.diplomska.rabota.diplomska.payload.request.JobUpdateRequest;

import java.util.List;
import java.util.Set;

public interface JobsService {

    public List<Job> getAllJobs();

    Job getJobById(Long id);

    String deleteJob(Long id);
    Job jobUpdate(Long id,JobUpdateRequest model);

    String getJobsCity(Long id);
    String categoryName(Long id);
    Set<String> getResponsibilities(Long id);

    Set<String> gerRequiredSkills(Long id);
    List<Skill> getTechnologies(Long id);
    Set<String> getWhatWeOffer(Long id);

}
