package bridgeProject;

import acptTests.auxiliary.DBRegisteredProjectInfo;
import acptTests.auxiliary.DBSuggestedProjectInfo;

public interface BridgeProject {


    void registerNewTechnicalAdviser(String user, String password);
    void addNewStudent(String user, String password);

    /*
     * Add new project
     * @param user username
     * @param pass password
     * @param suggestedProject contains suggested project info (used to reduce the amount of parameters)
     * @return If succeed returns unique project id (a positive number). Otherwise return 0.
     */
    int addNewProject(String user, String pass,DBSuggestedProjectInfo suggestedProject);

      /*
     * Register to new project by student team
     * @param user username
     * @param pass password
     * @param registeredProject contains registered project info (used to reduce the amount of parameters)
     * @return If succeed returns unique project id (a positive number). Otherwise return 0.
     */

    int registerToProject(String user, String pass, DBRegisteredProjectInfo registeredProject);






}