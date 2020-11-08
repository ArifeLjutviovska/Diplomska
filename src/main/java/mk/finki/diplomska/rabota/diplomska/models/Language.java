package mk.finki.diplomska.rabota.diplomska.models;

import javax.persistence.*;

@Entity
@Table(name="languages",uniqueConstraints = {
    @UniqueConstraint(columnNames = "name")
})
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
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
}
