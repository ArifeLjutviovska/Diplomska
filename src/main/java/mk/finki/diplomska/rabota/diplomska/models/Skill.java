package mk.finki.diplomska.rabota.diplomska.models;

import javax.persistence.*;

@Entity
@Table(name="skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String isFor;//frontend,backend etc.

    public Skill() {
    }

    public Skill(String name, String isFor) {
        this.name = name;
        this.isFor = isFor;
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

    public String getIsFor() {
        return isFor;
    }

    public void setIsFor(String isFor) {
        this.isFor = isFor;
    }
}
