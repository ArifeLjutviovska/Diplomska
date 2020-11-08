package mk.finki.diplomska.rabota.diplomska.models;

public enum JobType {
    Full_Time("Full Time"),
    Part_Time("Part Time"),
    Internship("Internship");

    public final String label;

    JobType(String label) {
        this.label=label;
    }
}
