package mk.finki.diplomska.rabota.diplomska.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="job_experience")
public class JobExperience {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String CompanyName;

    @ManyToOne
    private Category category;

    @Column(name="description", length=1024)
    private String description;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    Date DateStart;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    Date DateEnd;

    public JobExperience() {
    }

    public JobExperience(String companyName, Category category,String description, Date dateStart, Date dateEnd) {
        CompanyName = companyName;
        this.category=category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
