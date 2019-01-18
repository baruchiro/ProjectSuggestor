package ProjectSuggester;

public class Suggester {
	private final String suggesterName;
	private final String mail;
	private final Company company;
    private final String phone;

    public Suggester(String suggesterName, String mail, String suggesterPhone, Company company) {
		this.suggesterName = suggesterName;
		this.mail = mail;
		this.phone = suggesterPhone;
		this.company = company;
	}
}
