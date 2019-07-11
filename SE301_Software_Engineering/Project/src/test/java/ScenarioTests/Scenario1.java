package ScenarioTests;

import ActivityManagement.SpecialActivity;
import Repository.DBConnector;
import Repository.DBTransactions;
import Repository.RepositoryFacade;
import RequestManagement.SpecialActivityRequest;
import UserManagement.Instructor;
import UserManagement.VIPMember;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;

// <editor-fold defaultstate="collapsed" desc="SerdarJoinsASpecialActivity">
//
//Participating Actor Instances:
//	Serdar: VIPMember
//	Gizem: Instructor
//
//Flow of Events:
//	Serdar is a VIPMember of GEMS Fitness Center. 
//      He sees an advertisement of a new special activity that is introduced in GEMS. 
//      He decides to join it since he is a VIPMember. 
//      He logs into his GEMS account from his computer. 
//      He selects “Schedule Options” from GEMS menu. 
//      Then, he selects “Join A Special Activity” and he is given the list of current special activities. 
//      He chooses the activity he likes to participate and an appropriate slot for his schedule. 
//      GEMS takes the request send by Serdar and transmit it to Gizem. 
//      Gizem logs into her GEMS account and selects “Show Requests”. 
//      She approves the request of Serdar. 
//      Then, GEMS adds the activity to Serdar’s respective slot. 
// </editor-fold>
public class Scenario1 {

//    private VIPMember Mert;
//    private Instructor Emine;
//    
//    
//    private DBConnector connector;
//    private DBTransactions transactions;
//    private RepositoryFacade rp;
//   
//
//    public Scenario1() {
//        this.Mert = rp.getVipMember(1);
//        this.Emine = rp.getInstructor(1);
//    }
//
//    
//    @Before
//    public void setUp() {
//        connector = mock(DBConnector.class);
//        transactions = mock(transactions.getClass());
//        
//    }
//
//    
//    
//     @Test
//     public void test() {       
//        SpecialActivityRequest sar = null;
//        SpecialActivity selectedSpecialActivity = new SpecialActivity(0, "Golf", Emine, "Th-08-10");
//        SpecialActivityRequest request = new SpecialActivityRequest(0, Mert.getId(), Mert.getName(), Emine.getId(), selectedSpecialActivity.getId());
//        rp.insertSpecialActivityRequest(request);
//        List<SpecialActivityRequest> requests = rp.getSpecialActivityRequests(selectedSpecialActivity.getId());
//        
//        for (int i = 0; i < requests.size(); i++) {
//             if(requests.get(i).getId()==selectedSpecialActivity.getId())
//                 sar = requests.get(i);
//             
//        }
//         assertEquals(request, sar);
//       
//     }
}
