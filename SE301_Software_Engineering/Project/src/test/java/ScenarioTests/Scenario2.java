package ScenarioTests;

import Repository.RepositoryFacade;
import RequestManagement.VIPRequest;
import UserManagement.Registrar;
import UserManagement.VIPMember;
import java.lang.reflect.Member;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

// <editor-fold defaultstate="collapsed" desc="EmineBecomesAVIPMember">
//
//Participating Actor Instances:
//	Emine: Member
//	Serdar: VIPMember
//	Melike: Registrar
//
//Flow of Events:
//	A friend of Emine, Serdar, bumps into Emine while she was shopping in Niþantaþý. 
//      Serdar tells about this new activity which is only exclusive for VIP Members in GEMS. 
//      So, he recommends her to join this activity with him. 
//      However, Emine tells him that she is not a VIP Member. 
//      So, she logs into her GEMS account and selects “Pay For VIP”. 
//      Then, she selects “Pay With Card” option and types her information into the form GEMS provides. 
//      GEMS takes the request and send them to Melike. 
//      Then, Melike checks the validity of Emine’s credit card information. 
//      After completing the transaction, Melike approves Emine’s request of becoming a VIP Member by activating “Upgrade To VIP”. 
// </editor-fold>
public class Scenario2 {

//    private static RepositoryFacade rf;
//    private VIPMember Serdar;
//    private Member Emine;
//    private Registrar Melike;
//    private VIPRequest VIPR;
//    
//    
//    public Scenario2() {
//        
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//        rf = new RepositoryFacade();
//    }
//    
//    @Test
//    public void test() {
//        ArrayList<VIPRequest> VIPRequests;
//        VIPRequest vr = null;
//        this.Serdar = rf.getVipMember(1);
//        this.VIPR = new VIPRequest(0, 1, "Emine");
//        rf.insertVIPRequest(VIPR);
//        VIPRequests = rf.getVIPRequests();
//        for (VIPRequest VIPRequest : VIPRequests) {
//            if (VIPRequest.equals(VIPR)) {
//                vr = VIPRequest;
//            }
//        }
//        assertEquals(vr, VIPR);
//    }
    
}
