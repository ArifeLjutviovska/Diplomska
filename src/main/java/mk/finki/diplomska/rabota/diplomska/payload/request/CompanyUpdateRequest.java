package mk.finki.diplomska.rabota.diplomska.payload.request;

import mk.finki.diplomska.rabota.diplomska.models.DBFile;
import mk.finki.diplomska.rabota.diplomska.models.Job;

import javax.validation.constraints.Email;
import java.util.List;

public class CompanyUpdateRequest {
    private String companyName;
    @Email
    private String email;
    private String password;
    private String phoneNumber;
    private String contactName;
    private String address;
    private String city;
    private DBFile logo;
    private List<JobUpdateRequest> jobs;

    public CompanyUpdateRequest() {
    }

    public CompanyUpdateRequest(String companyName, @Email String email, String password, String phoneNumber, String contactName, String address, String city, DBFile logo, List<JobUpdateRequest> jobs) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.contactName = contactName;
        this.address = address;
        this.city = city;
        this.logo = logo;
        this.jobs = jobs;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
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

    public DBFile getLogo() {
        return logo;
    }

    public void setLogo(DBFile logo) {
        this.logo = logo;
    }

    public List<JobUpdateRequest> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobUpdateRequest> jobs) {
        this.jobs = jobs;
    }
}
