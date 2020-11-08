package mk.finki.diplomska.rabota.diplomska.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name="jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description", length=1024)
    private String description;


    @ManyToMany
    private List<Skill> technologies;

    @ElementCollection
    @Column(name="job_skills")
    private Set<String> requiredSkills;

    @ElementCollection
    @Column(name="job_offer")
    private Set<String> whatWeOffer;

    @ElementCollection
    @Column(name="job_responsibilities")
    private Set<String> responsibilities;



    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    Date publicationDateStart;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    Date publicationDateEnd;

    @ManyToOne
    Category category;


    @Size(max = 50)
    @Email
    private String applyEmail;


    private String company;

    @ManyToOne
    private City city;

    private JobType jobType;

    public Job() {
    }

    public Job(String title, @Size(min = 20, max = 600) String description, Set<String> requiredSkills, Set<String> whatWeOffer, Set<String> responsibilities,  Date publicationDateStart, Date publicationDateEnd, @Size(max = 50) @Email String applyEmail,Category category,String company,City city,JobType jobType,List<Skill> technologies) {
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.whatWeOffer = whatWeOffer;
        this.responsibilities = responsibilities;
        this.publicationDateStart = publicationDateStart;
        this.publicationDateEnd = publicationDateEnd;
        this.applyEmail = applyEmail;
        this.category=category;
        this.company=company;
        this.city=city;
        this.jobType=jobType;
        this.technologies=technologies;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Set<String> getWhatWeOffer() {
        return whatWeOffer;
    }

    public void setWhatWeOffer(Set<String> whatWeOffer) {
        this.whatWeOffer = whatWeOffer;
    }

    public Set<String> getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(Set<String> responsibilities) {
        this.responsibilities = responsibilities;
    }


    public Date getPublicationDateStart() {
        return publicationDateStart;
    }

    public void setPublicationDateStart(Date publicationDateStart) {
        this.publicationDateStart = publicationDateStart;
    }

    public Date getPublicationDateEnd() {
        return publicationDateEnd;
    }

    public void setPublicationDateEnd(Date publicationDateEnd) {
        this.publicationDateEnd = publicationDateEnd;
    }

    public String getApplyEmail() {
        return applyEmail;
    }

    public void setApplyEmail(String applyEmail) {
        this.applyEmail = applyEmail;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public List<Skill> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Skill> technologies) {
        this.technologies = technologies;
    }
}
