package mk.finki.diplomska.rabota.diplomska.payload.request;

import mk.finki.diplomska.rabota.diplomska.models.Category;

import java.util.List;

public class SkillsUpdateModel {

    private String name;

    private List<Category> categories;

    public SkillsUpdateModel() {
    }

    public SkillsUpdateModel(String name,List<Category> categories) {
        this.name = name;
        this.categories=categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
