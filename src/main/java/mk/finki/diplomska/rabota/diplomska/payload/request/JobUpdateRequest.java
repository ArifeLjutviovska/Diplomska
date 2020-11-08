package mk.finki.diplomska.rabota.diplomska.payload.request;

import mk.finki.diplomska.rabota.diplomska.models.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class JobUpdateRequest {


    private String title;


    private String description;

    private List<Skill> technologies;


    private Set<String> requiredSkills;

    private Set<String> whatWeOffer;

    private Set<String> responsibilities;



    private Category category;
    String publicationDateStart;
    private String companyName;

   String publicationDateEnd;


    @Size(max = 50)
    @Email
    private String applyEmail;

    private City city;

    private JobType jobType;

    public JobUpdateRequest() {
    }

    public JobUpdateRequest(String title, @Size(min = 20, max = 600) String description, Set<String> requiredSkills, Set<String> whatWeOffer, Set<String> responsibilities, City city,JobType jobType,String publicationDateStart, String publicationDateEnd, @Size(max = 50) @Email String applyEmail,Category category,String companyName,List<Skill> technologies) {
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.whatWeOffer = whatWeOffer;
        this.responsibilities = responsibilities;
        this.publicationDateStart = publicationDateStart;
        this.publicationDateEnd = publicationDateEnd;
        this.applyEmail = applyEmail;
        this.category=category;
        this.companyName=companyName;
        this.city=city;
        this.jobType=jobType;
        this.technologies=technologies;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Skill> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Skill> technologies) {
        this.technologies = technologies;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getPublicationDateStart() {
        return publicationDateStart;
    }

    public void setPublicationDateStart(String publicationDateStart) {
        this.publicationDateStart = publicationDateStart;
    }

    public String getPublicationDateEnd() {
        return publicationDateEnd;
    }

    public void setPublicationDateEnd(String publicationDateEnd) {
        this.publicationDateEnd = publicationDateEnd;
    }

    public String getApplyEmail() {
        return applyEmail;
    }

    public void setApplyEmail(String applyEmail) {
        this.applyEmail = applyEmail;
    }
}
