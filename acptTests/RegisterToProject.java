package acptTests;

import acptTests.auxiliary.*;
import org.junit.*;


public class RegisterToProject extends ProjectTest {
    private DBRegisteredProjectInfo registeredProject1;
    private int suggestedProject1ID;


    private DBSuggestedProjectInfo suggestedProject1 = new DBSuggestedProjectInfo();

    @Before
    public void setUp() {
        super.setUp();
        setUpSuggestedAndApprovedProjects();
    }


    @Before
    private void setUpSuggestedAndApprovedProjects() {
        suggestedProject1.firstName=DBData.names[0][USER_FirstName];
        suggestedProject1.lastName=DBData.names[0][USER_LastName];
        suggestedProject1.phone="03-7777777"  ;
        suggestedProject1.email="stam@ibm.com";
        suggestedProject1.organization="IBM";
        suggestedProject1.projectName="suggestedProject1 name";
        suggestedProject1.description="show description here";
        suggestedProject1.numberOfHours=289;

        suggestedProject1ID =this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS],suggestedProject1);

        //We assume that the project has been approved.
        registeredProject1 = new DBRegisteredProjectInfo();
        registeredProject1.projectId= suggestedProject1ID;
        registeredProject1.academicAdviser ="Danny";
    }

    @After
    public void tearDown() {
        suggestedProject1 =null;
    }

   @Test
    public void testRegisterProject(){
        registeredProject1.studentList.add(DBData.studentIDs[0]);
        registeredProject1.studentList.add(DBData.studentIDs[1]);

        int registeredProject=this.registerToProject(DBData.students[0][USER_USER],DBData.students[0][USER_PASS], registeredProject1);
        assertTrue(registeredProject>0);
    }

    @Test
    public void testRegistrationToSameProjects(){
        registeredProject1.studentList.add(DBData.studentIDs[0]);
        registeredProject1.studentList.add(DBData.studentIDs[1]);
        this.registerToProject(DBData.students[0][USER_USER],DBData.students[0][USER_PASS], registeredProject1);


        registeredProject1.studentList.clear();
        registeredProject1.studentList.add(DBData.studentIDs[2]);
        registeredProject1.studentList.add(DBData.studentIDs[3]);
        int registeredProject=this.registerToProject(DBData.students[1][USER_USER],DBData.students[1][USER_PASS], registeredProject1);
        assertEquals("Project cannot be  registered by more than one team", registeredProject,0);
    }


    @Test
    public void testRegisterProjectWithOnlyOneStudent(){
        registeredProject1.studentList.add(DBData.studentIDs[0]);
        int registeredProject=this.registerToProject(DBData.students[0][USER_USER],DBData.students[0][USER_PASS], registeredProject1);
        assertEquals("There must at least two registered students to the project",registeredProject,0);
    }


    @Test
    public void testRegistrationWithoutAcademicAdviser(){
        registeredProject1.academicAdviser=null;
        registeredProject1.studentList.add(DBData.studentIDs[0]);
        registeredProject1.studentList.add(DBData.studentIDs[1]);
        int registeredProject=this.registerToProject(DBData.students[0][USER_USER],DBData.students[0][USER_PASS], registeredProject1);
        assertEquals("Academic advisor in a project is mandatory",registeredProject,0);

        registeredProject1.academicAdviser="";
        registeredProject=this.registerToProject(DBData.students[0][USER_USER],DBData.students[0][USER_PASS], registeredProject1);
        assertEquals("Academic advisor in a project is mandatory",registeredProject,0);
    }



}


