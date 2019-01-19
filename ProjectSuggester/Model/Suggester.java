package ProjectSuggester.Model;

public class Suggester extends User {
    private final String suggesterName;

    private final Company company;
    private final String phone;

    public Suggester(String suggesterName, String mail, String suggesterPhone, Company company) {
        super(mail);
        this.suggesterName = suggesterName;

        this.phone = suggesterPhone;
        this.company = company;
    }

    public Company getCompany() {
        return this.company;
    }
}
