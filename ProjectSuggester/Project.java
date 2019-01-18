package ProjectSuggester;

import java.time.Duration;


public class Project {
    private final String projectName;
    private final String projectDescription;
    private final Duration hours;
    private final Suggester suggester;
    private int id;
    private Status status;

    public Project(int id, String projectName, String projectDescription, Duration hours, Suggester suggester) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.hours = hours;
        this.suggester = suggester;
        this.id = id;
        this.status = Status.InCheck;
        validate();
    }

    private void validate() {
        if (id <= 0 ||
                isNullOrEmpty(projectName) ||
                isNullOrEmpty(projectDescription) ||
                hours == null ||
                hours.compareTo(Duration.ofHours(300)) > 0 ||
                hours.compareTo(Duration.ofHours(200)) < 0
        )
            throw new IllegalArgumentException();
    }

    private boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public Duration getHours() {
        return hours;
    }

    public Suggester getSuggester() {
        return suggester;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {Reject, InCheck}
}
