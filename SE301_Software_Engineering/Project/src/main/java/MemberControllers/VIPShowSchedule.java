
package MemberControllers;
import ActivityManagement.Activity;
import ActivityManagement.SpecialActivity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import UserManagement.VIPMember;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

 
@ManagedBean(name="dtVIPShowSchedule")
@ViewScoped
public class VIPShowSchedule implements Serializable{
    
    private List<Activity> VIPActivities= new ArrayList<>();
    private List<SpecialActivity> VIPSpecialActivities = new ArrayList<>();;
    private List<Day> VIPSchedule = new ArrayList<>();    
    RepositoryFacade rp = new RepositoryFacade();
    VIPMember vip = rp.getVipMember(UserLogin.getUserLoggedIn());
    
    
    /**
     * This method returns the VIPMember's schedule as a List of Day
     * @return List of Day
     */
    public List<Day> getVIPSchedule() {
	VIPActivities= new ArrayList<>();
        VIPSpecialActivities = new ArrayList<>();
        VIPSchedule =new ArrayList<>();
        VIPActivities = rp.getMemberActivities(vip);
        VIPSpecialActivities = rp.getVIPMemberSpecialActvities(vip);
        VIPActivities.addAll(VIPSpecialActivities);
                
        VIPSchedule = initializeSchedule();
              
        return VIPSchedule;
    }
    
    /**
     * This method returns the day of a certain activity
     * @param activity
     * @return The integer value of the day
     */
    int findDay(Activity activity) {
        String slot = activity.getSlot();
        String[] parts = slot.split("-");
        String nameOfTheDay = parts[0];
        switch (nameOfTheDay) {
            case "Mo":
                return 1;
            case "Tu":
                return 2;
            case "We":
                return 3;
            case "Th":
                return 4;
            case "Fr":
                return 5;
            case "Sa":
                return 6;
            case "Su":
                return 7;
        }
        return -1;
    }
    
    /**
     * This method returns the slot of a certain activity
     * @param activity
     * @return The integer value of the slot
     */
    int findSlot(Activity activity) {
        String slot = activity.getSlot();
        String[] parts = slot.split("-");
        String start = parts[1];
        String end = parts[2];
        switch (start) {
            case "08":
                if ("10".equals(end)) {
                    return 0;
                }
            case "10":
                if ("12".equals(end)) {
                    return 1;
                }
            case "12":
                if ("14".equals(end)) {
                    return 2;
                }
            case "14":
                if ("16".equals(end)) {
                    return 3;
                }
            case "16":
                if ("18".equals(end)) {
                    return 4;
                }
            case "18":
                if ("20".equals(end)) {
                    return 5;
                }
            case "20":
                if ("22".equals(end)) {
                    return 6;
                }
        }
        return -1;
    }

    
    
    /**
     * This method initializes the VIPMember's schedule and returns it as a List of Day 
     * @return The schedule as a list
     */
    List initializeSchedule() {
        String[] slot0 = new String[8];
        String[] slot1 = new String[8];
        String[] slot2 = new String[8];
        String[] slot3 = new String[8];
        String[] slot4 = new String[8];
        String[] slot5 = new String[8];
        String[] slot6 = new String[8];
        
        slot0[0]="08-10";
        slot1[0]="10-12";
        slot2[0]="12-14";
        slot3[0]="14-16";
        slot4[0]="16-18";
        slot5[0]="18-20";
        slot6[0]="20-22";

        int slot;
        for (int i = 0; i < VIPActivities.size(); i++) {
            slot = findSlot(VIPActivities.get(i));
            switch (slot) {
                case 0:
                    slot0[findDay(VIPActivities.get(i))] = VIPActivities.get(i).getName();
                    break;
                case 1:
                    slot1[findDay(VIPActivities.get(i))] = VIPActivities.get(i).getName();
                    break;
                case 2:
                    slot2[findDay(VIPActivities.get(i))] = VIPActivities.get(i).getName();
                    break;
                case 3:
                    slot3[findDay(VIPActivities.get(i))] = VIPActivities.get(i).getName();
                    break;
                case 4:
                    slot4[findDay(VIPActivities.get(i))] = VIPActivities.get(i).getName();
                    break;
                case 5:
                    slot5[findDay(VIPActivities.get(i))] = VIPActivities.get(i).getName();
                    break;
                case 6:
                    slot6[findDay(VIPActivities.get(i))] = VIPActivities.get(i).getName();
            }

        }

        VIPSchedule.add(new Day(slot0));
        VIPSchedule.add(new Day(slot1));
        VIPSchedule.add(new Day(slot2));
        VIPSchedule.add(new Day(slot3));
        VIPSchedule.add(new Day(slot4));
        VIPSchedule.add(new Day(slot5));
        VIPSchedule.add(new Day(slot6));

        return VIPSchedule;

    }

    /**
     * This method returns the VIPMember's activities
     * @return VIPActivities
     */
    public List<Activity> getVIPActivities() {
        return VIPActivities;
    }

    /**
     * This method sets the VIPMember's activities
     * @param VIPActivities 
     */
    public void setVIPActivities(List<Activity> VIPActivities) {
        this.VIPActivities = VIPActivities;
    }

    /**
     * This method returns the VIPMember's special activities
     * @return VIPSpecialActivities
     */
    public List<SpecialActivity> getVIPSpecialActivities() {
        return VIPSpecialActivities;
    }

    /**
     * This method sets the VIPMember's special activities
     * @param VIPSpecialActivities 
     */
    public void setVIPSpecialActivities(List<SpecialActivity> VIPSpecialActivities) {
        this.VIPSpecialActivities = VIPSpecialActivities;
    }
    
    
    
    
}
