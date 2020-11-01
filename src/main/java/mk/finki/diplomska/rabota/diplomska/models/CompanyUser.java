package mk.finki.diplomska.rabota.diplomska.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name="companies",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "email")
        })
public class CompanyUser extends User {

    @NotBlank
    @Size(max=20)
    private String phoneNumber;

    @NotBlank
    @Size(max=30)
    private String ContactName;

    @NotBlank
    @Size(max=50)
    private String Address;

    @NotBlank
    @Size(max=20)
    private String City;

    @OneToOne
    private DBFile Logo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "company_roles",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "company_jobs",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "job_id"))
    private List<Job> jobs;

    public CompanyUser(){

    }

    public CompanyUser(String username, String email, String password) {
        super(UserType.Company, username, email, password);


    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public DBFile getLogo() {
        return Logo;
    }

    public void setLogo(DBFile logo) {
        Logo = logo;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
