package mk.finki.diplomska.rabota.diplomska.payload.request;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class JobUpdateRequest {


    private String title;

    @Size(min=20,max = 600)
    private String description;


    private Set<String> requiredSkills;

    private Set<String> whatWeOffer;

    private Set<String> responsibilities;


    private String position;

    private String location;

    String publicationDateStart;

   String publicationDateEnd;


    @Size(max = 50)
    @Email
    private String applyEmail;

    public JobUpdateRequest() {
    }

    public JobUpdateRequest(String title, @Size(min = 20, max = 600) String description, Set<String> requiredSkills, Set<String> whatWeOffer, Set<String> responsibilities, String position, String location, String publicationDateStart, String publicationDateEnd, @Size(max = 50) @Email String applyEmail) {
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
