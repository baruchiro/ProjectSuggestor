package UnitTests;

import ProjectSuggester.Model.Company;
import ProjectSuggester.Model.Project;
import ProjectSuggester.Model.Suggester;
import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.Locale;
import java.util.Random;

public class ProjectBuilder implements DtoBuilder<Project> {
    private String suggesterCompany;
    private String suggesterPhone;
    private String suggesterName;
    private String projectName;
    private String projectDescription;
    private Duration hours;
    private int id;
    private Project.Status status;
    private String suggesterMail;
    private boolean isCompany;

    private ProjectBuilder() {
        Faker faker = new Faker(Locale.forLanguageTag("he"));

        id = new Random().nextInt(Integer.MAX_VALUE);
        projectName = faker.app().name();
        projectDescription = faker.harryPotter().quote();
        hours = Duration.ofHours(250);
        suggesterName = faker.name().fullName();
        suggesterName = faker.internet().emailAddress();
        suggesterPhone = faker.phoneNumber().phoneNumber();
        suggesterCompany = faker.company().name();
        isCompany = true;
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
        var company = isCompany ? new Company(suggesterCompany) : null;
        var project = new Project(projectName, projectDescription, hours, new Suggester(suggesterName, suggesterMail, suggesterPhone, company));
        project.setId(id);
        return project;
    }

    public ProjectBuilder withoutCompany() {
        this.isCompany = false;
        return this;
    }
}
