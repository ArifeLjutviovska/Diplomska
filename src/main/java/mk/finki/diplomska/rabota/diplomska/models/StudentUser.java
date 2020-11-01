package mk.finki.diplomska.rabota.diplomska.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="students",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "email")
        })
public class StudentUser extends User{

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "student_roles",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    private DBFile img;

    @OneToOne
    private DBFile CV;

    @ManyToMany
    private List<Skill> skills;

    @ManyToMany
    private Set<Subject> subjects;

    @OneToMany
    private List<JobExperience> experience;

    public StudentUser(){

    }

    public StudentUser(String username, String email, String password){
        super(UserType.Student,username,email,password);
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<JobExperience> getExperience() {
        return experience;
    }

    public void setExperience(List<JobExperience> experience) {
        this.experience = experience;
    }
}
