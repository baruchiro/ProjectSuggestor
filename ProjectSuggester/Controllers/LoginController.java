package ProjectSuggester.Controllers;

import ProjectSuggester.DB;

public class LoginController {
    public void login(String mail){
        DB.getInstance().getUserByMail(mail).setConnected(true);
    }
    public boolean isLogin(String mail) {
        return false;
    }
}
