package UnitTests;

import ProjectSuggester.Company;
import ProjectSuggester.Project;
import ProjectSuggester.Suggester;
import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.Locale;
import java.util.Random;

public class ProjectBuilder {
    private String suggesterCompany;
    private String suggesterPhone;
    private String suggesterName;
    private String projectName;
    private String projectDescription;
    private Duration hours;
    private int id;
    private Project.Status status;
    private String suggesterMail;

    private ProjectBuilder() {
        Faker faker = new Faker(Locale.forLanguageTag("he"));

        id = new Random().nextInt();
        projectName = faker.app().name();
        projectDescription = faker.harryPotter().quote();
        hours = Duration.ofHours(250);
        suggesterName = faker.name().fullName();
        suggesterName = faker.internet().emailAddress();
        suggesterPhone = faker.phoneNumber().phoneNumber();
        suggesterCompany = faker.company().name();
        suggesterMail = faker.internet().emailAddress();
    }

    public static ProjectBuilder LoadDefaults() {
        return new ProjectBuilder();
    }

    public String getSuggesterCompany() {
        return suggesterCompany;
    }

    public ProjectBuilder setSuggesterCompany(String suggesterCompany) {
        this.suggesterCompany = suggesterCompany;
        return this;
    }

    public String getSuggesterPhone() {
        return suggesterPhone;
    }

    public ProjectBuilder setSuggesterPhone(String suggesterPhone) {
        this.suggesterPhone = suggesterPhone;
        return this;
    }

    public String getSuggesterName() {
        return suggesterName;
    }

    public ProjectBuilder setSuggesterName(String suggesterName) {
        this.suggesterName = suggesterName;
        return this;
    }

    public String getProjectName() {
        return projectName;
    }

    public ProjectBuilder setProjectName(String projectName) {
        this.projectName = projectName;
        return this;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public ProjectBuilder setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
        return this;
    }

    public Duration getHours() {
        return hours;
    }

    public ProjectBuilder setHours(Duration hours) {
        this.hours = hours;
        return this;
    }

    public int getId() {
        return id;
    }

    public ProjectBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public Project.Status getStatus() {
        return status;
    }

    public void setStatus(Project.Status status) {
        this.status = status;
    }

    public String getSuggesterMail() {
        return suggesterMail;
    }

    public void setSuggesterMail(String suggesterMail) {
        this.suggesterMail = suggesterMail;
    }

    public Project Build() {
        return new Project(id, projectName, projectDescription, hours, new Suggester(suggesterName, suggesterMail, suggesterPhone, new Company(suggesterCompany)));
    }
}
