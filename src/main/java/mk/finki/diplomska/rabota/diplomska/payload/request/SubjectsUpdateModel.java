package mk.finki.diplomska.rabota.diplomska.payload.request;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.validation.constraints.Size;
import java.util.List;

public class SubjectsUpdateModel {

    private String name;

    @Size(min=20,max=300)
    private String description;


    private List<SkillsUpdateModel> skills;

    public SubjectsUpdateModel() {
    }

    public SubjectsUpdateModel(String name, @Size(min = 20, max = 300) String description, List<SkillsUpdateModel> skills) {
        this.name = name;
        this.description = description;
        this.skills = skills;
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

    public List<SkillsUpdateModel> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsUpdateModel> skills) {
        this.skills = skills;
    }
}
