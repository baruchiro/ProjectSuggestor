package ProjectSuggester.Model;

import ProjectSuggester.Validators;

public class User implements Model {
    private final String mail;
    private int id;
    private boolean connected;

    public User(String mail){
        this.mail = mail;
        this.id = -1;
    }

    public String getMail() {
        return mail;
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
}
