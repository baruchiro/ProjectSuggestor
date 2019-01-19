package ProjectSuggester.Controllers;

import ProjectSuggester.DB;
import ProjectSuggester.Exceptions.UserNotConnectedException;
import ProjectSuggester.Model.Company;
import ProjectSuggester.Model.Project;
import ProjectSuggester.Model.Suggester;

import java.time.Duration;

public class UsersController {

    public int Add(String name, String mail, String phone, String companyName) {
        var company = new Company(companyName);
        var suggester = new Suggester(name, mail, phone, company);

        return DB.getInstance().addUser(suggester);
    }
}
