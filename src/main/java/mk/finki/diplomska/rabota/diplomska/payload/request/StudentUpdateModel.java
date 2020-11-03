package mk.finki.diplomska.rabota.diplomska.payload.request;

import mk.finki.diplomska.rabota.diplomska.models.DBFile;
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

    public StudentUpdateModel() {
    }

    public StudentUpdateModel(String studentName, @Email String email, String password, DBFile img, DBFile CV, List<SkillsUpdateModel> skills, Set<SubjectsUpdateModel> subjects, List<JobExperienceUpdateModel> experience) {
        this.studentName = studentName;
        this.email = email;
        this.password = password;
        this.img = img;
        this.CV = CV;
        this.skills = skills;
        this.subjects = subjects;
        this.experience = experience;
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
}
