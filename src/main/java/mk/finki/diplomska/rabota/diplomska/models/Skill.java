package mk.finki.diplomska.rabota.diplomska.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="skills", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Category> categoryList;

    public Skill() {
    }

    public Skill(String name, List<Category> categories) {
        this.name = name;
        this.categoryList=categories;
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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
