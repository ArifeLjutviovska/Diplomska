package mk.finki.diplomska.rabota.diplomska.controllers;

import mk.finki.diplomska.rabota.diplomska.models.Job;
import mk.finki.diplomska.rabota.diplomska.services.Impl.JobServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    private final JobServiceImpl jobService;


    public JobsController(JobServiceImpl jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAllJobs(){
        return this.jobService.getAllJobs();
    }


}
