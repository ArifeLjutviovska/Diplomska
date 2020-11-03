package mk.finki.diplomska.rabota.diplomska.payload.request;

public class SkillsUpdateModel {

    private String name;

    private String isFor;//frontend,backend etc.

    public SkillsUpdateModel() {
    }

    public SkillsUpdateModel(String name, String isFor) {
        this.name = name;
        this.isFor = isFor;
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
