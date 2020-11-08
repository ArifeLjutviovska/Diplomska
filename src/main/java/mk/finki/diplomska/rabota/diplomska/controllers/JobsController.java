package mk.finki.diplomska.rabota.diplomska.controllers;

import mk.finki.diplomska.rabota.diplomska.models.Job;
import mk.finki.diplomska.rabota.diplomska.models.JobType;
import mk.finki.diplomska.rabota.diplomska.models.Skill;
import mk.finki.diplomska.rabota.diplomska.payload.request.JobUpdateRequest;
import mk.finki.diplomska.rabota.diplomska.services.Impl.JobServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/jobs")
@CrossOrigin("*")
public class JobsController {
    private final JobServiceImpl jobService;


    public JobsController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs(){
        return this.jobService.getAllJobs();
    }

    @GetMapping("/jobType")
    public String getJobType(@RequestParam JobType type){
        return type.label;
    }

    @GetMapping("{id}")
    public Job getJob(@PathVariable("id") Long id){
        return this.jobService.getJobById(id);
    }

    @PatchMapping("{id}")
    public Job jobUpdate(@PathVariable("id")Long id, @RequestBody JobUpdateRequest model){
        return this.jobService.jobUpdate(id,model);
    }

    @DeleteMapping("{id}")
    public String deleteJob(@PathVariable("id") Long id){
        return this.jobService.deleteJob(id);
    }

    @GetMapping("{id}/cityName")
    public String  getJobsCity(@PathVariable("id") Long id){

        return this.jobService.getJobsCity(id);
    }
    @GetMapping("{id}/categoryName")
    public String  getJobsCategory(@PathVariable("id") Long id){

        return this.jobService.categoryName(id);
    }
    @GetMapping("{id}/jobResponsibilities")
    public Set<String> getJobsResponsibilities(@PathVariable("id") Long id){

        return this.jobService.getResponsibilities(id);
    }

    @GetMapping("{id}/whatWeOffer")
    public Set<String> getJobsWhatWeOffer(@PathVariable("id") Long id){

        return this.jobService.getWhatWeOffer(id);
    }
    @GetMapping("{id}/technologies")
    public List<Skill> getJobTechnologies(@PathVariable("id") Long id){

        return this.jobService.getTechnologies(id);
    }
    @GetMapping("{id}/requiredSkills")
    public Set<String> getJobsSkills(@PathVariable("id") Long id){

        return this.jobService.gerRequiredSkills(id);
    }


}
