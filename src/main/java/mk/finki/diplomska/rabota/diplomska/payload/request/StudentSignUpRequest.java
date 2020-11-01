package mk.finki.diplomska.rabota.diplomska.payload.request;



import mk.finki.diplomska.rabota.diplomska.models.DBFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;


public class StudentSignUpRequest {



    @NotBlank
    private String name;


    @Email
    @NotBlank
    private String email;



    @NotBlank
    private String password;

    private Set<String> role;

    private DBFile img;
    private DBFile CV;


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
