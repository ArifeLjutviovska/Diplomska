package mk.finki.diplomska.rabota.diplomska.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="students_job_experience")
public class JobExperience {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String CompanyName;

    private String jobIsFor;
    private String description;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    Date DateStart;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    Date DateEnd;

    public JobExperience() {
    }

    public JobExperience(String companyName, String jobIsFor,String description, Date dateStart, Date dateEnd) {
        CompanyName = companyName;
        this.jobIsFor = jobIsFor;
        DateStart = dateStart;
        DateEnd = dateEnd;
        this.description=description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getJobIsFor() {
        return jobIsFor;
    }

    public void setJobIsFor(String jobIsFor) {
        this.jobIsFor = jobIsFor;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }

    public Date getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        DateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
