package acptTests;

import acptTests.auxiliary.*;
import bridgeProject.BridgeProject;
import junit.framework.TestCase;

public class ProjectTest extends TestCase {
	private BridgeProject bridge;

	public static final int USER_FirstName=0, USER_LastName =1,USER_USER=0, USER_PASS=1;
	

    public void setUp() {
        this.bridge=Driver.getBridge();

        setUpUsers();
        setUpStudents();
    }

    private void setUpUsers() {
   		for(String[] userInfo : DBData.users) {
   			this.bridge.registerNewTechnicalAdviser(userInfo[USER_USER], userInfo[USER_PASS]);
   		}
   	}

    private void setUpStudents() {
        for(String[] studentInfo : DBData.students) {
            this.bridge.addNewStudent(studentInfo[USER_USER], studentInfo[USER_PASS]);
     }
    }

    public int addProject(String user, String pass,DBSuggestedProjectInfo DBSuggestedProjectInfo) {
   		return this.bridge.addNewProject(user,pass, DBSuggestedProjectInfo);
   	}


    public int registerToProject(String user, String pass,DBRegisteredProjectInfo projectDB) {
    		return this.bridge.registerToProject(user,pass, projectDB);
    	}



}