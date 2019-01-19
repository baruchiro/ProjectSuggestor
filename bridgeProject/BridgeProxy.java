package bridgeProject;


import acptTests.auxiliary.DBRegisteredProjectInfo;
import acptTests.auxiliary.DBSuggestedProjectInfo;


public class BridgeProxy implements BridgeProject {
	private BridgeProject real;

	public BridgeProxy() {
		this.real=null;
	}

	public void setRealBridge(BridgeProject real) {
		this.real = real;
	}

    public void registerNewTechnicalAdviser(String user, String password) {
        if(this.real!=null)
            real.registerNewTechnicalAdviser(user,password);
    }

    public void addNewStudent(String user, String password) {
        if(this.real!=null)
            real.addNewStudent(user,password);
    }



    public int addNewProject(String user, String pass, DBSuggestedProjectInfo suggestedProject) {
        if(this.real!=null)
           return real.addNewProject(user,pass,suggestedProject);
        else  return 1;
    }


    public int registerToProject(String user, String pass, DBRegisteredProjectInfo registeredProject) {
        if(this.real!=null)
            return real.registerToProject(user,pass,registeredProject);
        else return 1;

    }




}