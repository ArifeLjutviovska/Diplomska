package mk.finki.diplomska.rabota.diplomska.payload.request;


public class JobExperienceUpdateModel {

    private String companyName;

    private String jobIsFor;
    private String description;


    String dateStart;

    String dateEnd;

    public JobExperienceUpdateModel() {
    }

    public JobExperienceUpdateModel(String CompanyName, String jobIsFor, String description, String DateStart, String DateEnd) {
        this.companyName = CompanyName;
        this.jobIsFor = jobIsFor;
        this.description = description;
        this.dateStart = DateStart;
        this.dateEnd = DateEnd;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
