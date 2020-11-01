package mk.finki.diplomska.rabota.diplomska.payload.request;

import mk.finki.diplomska.rabota.diplomska.models.DBFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class CompanySignUpRequest {

    @NotBlank
    private String name;


    @Email
    @NotBlank
    private String email;



    @NotBlank
    private String password;

    private Set<String> role;


    @Size(max=20)
    private String phoneNumber;


    @Size(max=30)
    private String contactName;


    @Size(max=50)
    private String address;


    @Size(max=20)
    private String city;

    private DBFile logo;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
