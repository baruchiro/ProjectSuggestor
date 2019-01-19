package ProjectSuggester.Controllers;

import ProjectSuggester.DB;

public class LoginController {
    public void login(String userName, String password){
        DB.getInstance().getUser(userName).Login(password);
    }
    public boolean isLogin(String username) {
        return DB.getInstance().getUser(username).isConnected();
    }
}
