package mk.finki.diplomska.rabota.diplomska.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class JobExperienceUpdateModel {

    private String CompanyName;

    private String jobIsFor;
    private String description;


    String DateStart;

    String DateEnd;

    public JobExperienceUpdateModel() {
    }

    public JobExperienceUpdateModel(String companyName, String jobIsFor, String description, String dateStart, String dateEnd) {
        CompanyName = companyName;
        this.jobIsFor = jobIsFor;
        this.description = description;
        DateStart = dateStart;
        DateEnd = dateEnd;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String dateEnd) {
        DateEnd = dateEnd;
    }
}
