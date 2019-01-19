package ProjectSuggester.Model;

import ProjectSuggester.Validators;

public class User implements Model {
    private final String username;
    private String password;
    private int id;
    private boolean connected;

    public User(String username, String password) {
        this(username);
        this.password = password;
    }

    public User(String username) {
        this.username = username;
        this.id = -1;
    }

    public String getUsername() {
        return username;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (this.id > 0) throw new UnsupportedOperationException("id is already set");
        Validators.Throw.NotPositive(id);
        this.id = id;
    }

    public void Login(String password) {
        if (this.password.equals(password)) this.connected = true;
    }
}
