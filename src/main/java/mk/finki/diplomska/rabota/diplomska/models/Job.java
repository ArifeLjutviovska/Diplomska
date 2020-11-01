package mk.finki.diplomska.rabota.diplomska.models;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
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

    @Column(name="description")
    @Size(min=20,max = 600)
    private String description;



    @ElementCollection
    @Column(name="job_skills")
    private Set<String> requiredSkills;

    @ElementCollection
    @Column(name="job_offer")
    private Set<String> whatWeOffer;

    @ElementCollection
    @Column(name="job_responsibilities")
    private Set<String> responsibilities;


    private String position;

    private String location;

    @Temporal(TemporalType.DATE)
    Date publicationDateStart;

    @Temporal(TemporalType.DATE)
    Date publicationDateEnd;


    @Size(max = 50)
    @Email
    private String applyEmail;

    public Job() {
    }

    public Job(String title, @Size(min = 20, max = 600) String description, Set<String> requiredSkills, Set<String> whatWeOffer, Set<String> responsibilities, String position, String location, Date publicationDateStart, Date publicationDateEnd, @Size(max = 50) @Email String applyEmail) {
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.whatWeOffer = whatWeOffer;
        this.responsibilities = responsibilities;
        this.position = position;
        this.location = location;
        this.publicationDateStart = publicationDateStart;
        this.publicationDateEnd = publicationDateEnd;
        this.applyEmail = applyEmail;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
