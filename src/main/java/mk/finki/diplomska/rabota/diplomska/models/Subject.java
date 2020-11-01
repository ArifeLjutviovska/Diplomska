package mk.finki.diplomska.rabota.diplomska.models;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Size(min=20,max=300)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> skills;

    public Subject() {
    }

    public Subject(String name, @Size(min = 20, max = 300) String description, List<Skill> skills) {
        this.name = name;
        this.description = description;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
