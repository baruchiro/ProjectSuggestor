package acptTests;


import acptTests.auxiliary.*;
import org.junit.*;

public class SuggestedProjectTest extends ProjectTest {

    private DBSuggestedProjectInfo suggestedProject1;
    private DBSuggestedProjectInfo suggestedProject2;
    private DBSuggestedProjectInfo suggestedProject3;
    private DBSuggestedProjectInfo suggestedProject4;

    @Before
    public void setUp() {
        super.setUp();
        setUpGoodProjects();
    }

    @Before
    private void setUpGoodProjects(){
        suggestedProject1 = new DBSuggestedProjectInfo();
        suggestedProject2 = new DBSuggestedProjectInfo();
        suggestedProject3 = new DBSuggestedProjectInfo();
        suggestedProject4 = new DBSuggestedProjectInfo();


        suggestedProject1.firstName=DBData.names[0][USER_FirstName];
        suggestedProject1.lastName=DBData.names[0][USER_LastName];
        suggestedProject1.phone="03-7777777"  ;
        suggestedProject1.email="stam@ibm.com";
        suggestedProject1.organization="IBM";
        suggestedProject1.projectName="suggestedProject1 name";
        suggestedProject1.description="show description here";
        suggestedProject1.numberOfHours=300;


        suggestedProject2.firstName=DBData.names[1][USER_FirstName];
        suggestedProject2.lastName=DBData.names[1][USER_LastName];
        suggestedProject2.phone="03-6666666"  ;
        suggestedProject2.email="stam@microsoft.com";
        suggestedProject2.organization="MicroSoft";
        suggestedProject2.projectName="suggestedProject2 name";
        suggestedProject2.description="show description here";
        suggestedProject2.numberOfHours=250;


        suggestedProject3.firstName=DBData.names[2][USER_FirstName];
        suggestedProject3.lastName=DBData.names[2][USER_LastName];
        suggestedProject3.phone="03-7777777"  ;
        suggestedProject3.email="stam@ibm.com";
        suggestedProject3.organization="IBM";
        suggestedProject3.projectName="suggestedProject1 name";
        suggestedProject3.description="show description here";
        suggestedProject3.numberOfHours=260;


        suggestedProject4.firstName=DBData.names[0][USER_FirstName];
        suggestedProject4.lastName=DBData.names[0][USER_LastName];
        suggestedProject4.phone="03-7777777"  ;
        suggestedProject4.email="stam@ibm.com";
        suggestedProject4.organization="IBM";
        suggestedProject4.projectName="suggestedProject1 name";
        suggestedProject4.description="show description here";
        suggestedProject4.numberOfHours=290;

    }

    @After
    public void tearDown() {
        suggestedProject1 =null;
        suggestedProject2 =null;
        suggestedProject3 =null;
    }


    @Test
    public void testAddSuggestedProject(){
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertTrue(projectId1  > 0);
    }

    @Test
    public void testUniqueIds(){
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        int projectId2 = this.addProject(DBData.users[1][USER_USER], DBData.users[1][USER_PASS], suggestedProject2);
        assertTrue("Different projects must have different ids !", projectId1 != projectId2);
    }


    @Test
    public void testAddSuggestedProjectMissingFirstName(){

        suggestedProject1.firstName=null;
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals("Missing first name",projectId1,0);

        suggestedProject1.firstName="";

        projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
       assertEquals("Missing first name",projectId1,0);
    }


    @Test
    public void testAddSuggestedProjectMissingLastName(){

        suggestedProject1.lastName=null;
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals("Missing last name",projectId1,0);

        suggestedProject1.lastName="";

        projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
       assertEquals("Missing last name",projectId1,0);
    }



    @Test
    public void testAddSuggestedProjectMissingPhone(){

        suggestedProject1.phone=null;
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals("Missing phone number",projectId1,0);

        suggestedProject1.phone="";

        projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
       assertEquals("Missing phone number",projectId1,0);
    }

    @Test
    public void testAddSuggestedProjectMissingEmail(){

        suggestedProject1.email=null;
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals("Missing Email",projectId1,0);

        suggestedProject1.email="";

        projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
       assertEquals("Missing Email",projectId1,0);
    }


    @Test
    public void testAddSuggestedProjectMissingProjectName(){

        suggestedProject1.projectName=null;
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals(projectId1,0);

        suggestedProject1.projectName="";

        projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
       assertEquals(projectId1,0);
    }

    @Test
    public void testAddProjectWithoutOrganization(){
        suggestedProject1.organization="";
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertTrue("Name of the organization is not mandatory",projectId1  > 0);

        suggestedProject2.organization=null;
        projectId1 = this.addProject(DBData.users[1][USER_USER], DBData.users[1][USER_PASS], suggestedProject2);
        assertTrue("Name of the organization is not mandatory",projectId1  > 0);



    }


    @Test
    public void testAddProjectWithWrongUserOrPassword(){
        int projectId=this.addProject("NoExistUser", DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals(0,projectId);


        projectId = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS]+"password", suggestedProject1);
        assertEquals(0,projectId);


        projectId = this.addProject(DBData.users[1][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals(0,projectId);



        projectId = this.addProject(null, DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals(0,projectId);


        projectId = this.addProject(DBData.users[0][USER_USER], null, suggestedProject1);
        assertEquals(0,projectId);

    }

    @Test
    public void testUniqueProjectNamesOfSameOrganizationInSameYear(){
        this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        int projectId2 = this.addProject(DBData.users[1][USER_USER], DBData.users[1][USER_PASS], suggestedProject3);
        assertEquals("Different projects of the same organization in the same calender year must have different names!",projectId2 ,0);
    }

    @Test
    public void testUniqueProjectNamesOfSameUserInSameYear(){
        this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        int projectId2 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject4);
        assertEquals("Different projects of the same user in the same calender year must have different names!",projectId2,0);
    }



    @Test
    public void testNumberOfHours(){
        suggestedProject1.numberOfHours=199;
        int projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals(projectId1,0);

        suggestedProject1.numberOfHours=301;
        projectId1 = this.addProject(DBData.users[0][USER_USER], DBData.users[0][USER_PASS], suggestedProject1);
        assertEquals(projectId1,0);



    }

}
























