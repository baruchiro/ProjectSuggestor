package ProjectSuggester.Controllers;

import ProjectSuggester.DB;
import ProjectSuggester.Model.Company;
import ProjectSuggester.Model.Mentor;
import ProjectSuggester.Model.Suggester;
import ProjectSuggester.Model.Student;

public class UsersController {

    public int Add(String name, String mail, String phone, String companyName) {
        var company = new Company(companyName);
        var suggester = new Suggester(name, mail, phone, company);

        return DB.getInstance().addUser(suggester);
    }

    public void AddMentor(String user, String password) {
        DB.getInstance().addUser(new Mentor(user, password));
    }

    public void AddStudent(String user, String password) {
        DB.getInstance().addUser(new Student(user, password));
    }
}
