package mk.finki.diplomska.rabota.diplomska.payload.request;


import mk.finki.diplomska.rabota.diplomska.models.Category;

public class JobExperienceUpdateModel {

    private String companyName;

    private Category category;
    private String description;


    String dateStart;

    String dateEnd;

    public JobExperienceUpdateModel() {
    }

    public JobExperienceUpdateModel(String CompanyName, Category category, String description, String DateStart, String DateEnd) {
        this.companyName = CompanyName;
        this.category=category;
        this.description = description;
        this.dateStart = DateStart;
        this.dateEnd = DateEnd;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
