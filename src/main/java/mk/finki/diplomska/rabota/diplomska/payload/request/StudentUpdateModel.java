package mk.finki.diplomska.rabota.diplomska.payload.request;

import mk.finki.diplomska.rabota.diplomska.models.*;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

public class StudentUpdateModel {

    private String studentName;

    @Email
    private String email;
    private String password;
    private DBFile img;
    private DBFile CV;
    private List<SkillsUpdateModel> skills;
    private Set<SubjectsUpdateModel> subjects;
    private List<JobExperienceUpdateModel> experience;
    private List<Language> languages;
    private int yearOfStudies;
    private Branch branch;
    private String  pol;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private String summary;
    private String city;

    public StudentUpdateModel() {
    }

    public StudentUpdateModel(String studentName, @Email String email, String password, DBFile img, DBFile CV, List<SkillsUpdateModel> skills, Set<SubjectsUpdateModel> subjects, List<JobExperienceUpdateModel> experience,List<Language> languages,int years,Branch branch,String pol,String contactEmail,String contactPhone,String address,String city,String summary) {
        this.studentName = studentName;
        this.email = email;
        this.password = password;
        this.img = img;
        this.CV = CV;
        this.skills = skills;
        this.subjects = subjects;
        this.experience = experience;
        this.languages=languages;
        this.yearOfStudies=years;
        this.branch=branch;
        this.pol=pol;
        this.city=city;
        this.contactEmail=contactEmail;
        this.contactPhone=contactPhone;
        this.address=address;
        this.summary=summary;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public int getYearOfStudies() {
        return yearOfStudies;
    }

    public void setYearOfStudies(int yearOfStudies) {
        this.yearOfStudies = yearOfStudies;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DBFile getImg() {
        return img;
    }

    public void setImg(DBFile img) {
        this.img = img;
    }

    public DBFile getCV() {
        return CV;
    }

    public void setCV(DBFile CV) {
        this.CV = CV;
    }

    public List<SkillsUpdateModel> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsUpdateModel> skills) {
        this.skills = skills;
    }

    public Set<SubjectsUpdateModel> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectsUpdateModel> subjects) {
        this.subjects = subjects;
    }

    public List<JobExperienceUpdateModel> getExperience() {
        return experience;
    }

    public void setExperience(List<JobExperienceUpdateModel> experience) {
        this.experience = experience;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
