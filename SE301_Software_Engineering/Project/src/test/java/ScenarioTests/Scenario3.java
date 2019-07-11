package ScenarioTests;

import SchedulerControllers.UpdateActivityControl;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;


// <editor-fold defaultstate="collapsed" desc="PilatesInstructorChanges">
//
//Participating Actor Instances:
//	Gizem, Emre: Instructor
//	Nazlý: Scheduler
//
//Flow of Events:
//	GEMS changes the instructor of Pilates Activity and the current instructor of Pilates, Gizem, is off for maternity leave. 
//      So, Nazlý logs into her GEMS account and selects “Activity Options” and she selects “Update Activity”. 
//      Then, Nazlý selects Pilates from the list of activities GEMS provides. 
//      Nazlý changes the instructor information from the form and saves it.
// </editor-fold>
public class Scenario3 {
    
//    private UpdateActivityControl update;
//
//    @Before
//    public void setUp() {
//        update = mock(UpdateActivityControl.class);
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    @Test
//    public void Test1() {
//        update.setInstructor("Gizem");
//        update.setInstructor("Serdar");
//        assertTrue(update.getInstructor().equals("Serdar"));
//    }
}
