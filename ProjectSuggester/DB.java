package ProjectSuggester;

import java.util.ArrayList;
import java.util.List;

public class DB {
    private static DB ourInstance = new DB();
    public List<Project> projects;

    public static DB getInstance() {
        return ourInstance;
    }

    private DB() {
        this.projects = new ArrayList<>();
    }
}
