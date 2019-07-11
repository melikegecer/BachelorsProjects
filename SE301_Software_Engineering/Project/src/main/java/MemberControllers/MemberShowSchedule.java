package MemberControllers;

import ActivityManagement.Activity;
import LoginController.UserLogin;
import Repository.RepositoryFacade;
import UserManagement.Member;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "dtMemberShowSchedule")
@ViewScoped
public class MemberShowSchedule implements Serializable {

    private List<Activity> MembersActivities = new ArrayList<>();
    private List<Day> memberSchedule = new ArrayList<>();
    RepositoryFacade rp = new RepositoryFacade();

    /**
     * This method returns the member's schedule as a List of Day
     *
     * @return List of Day
     */
    public List<Day> getMemberSchedule() {
        MembersActivities = new ArrayList<>();
        memberSchedule = new ArrayList<>();
        Member m = rp.getMember(UserLogin.getUserLoggedIn());
        MembersActivities = rp.getMemberActivities(m);
        memberSchedule = initializeSchedule();
        return memberSchedule;
    }

    /**
     * This method returns the day of a certain activity
     *
     * @param activity
     * @return int
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
     *
     * @param activity
     * @return int
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
     * This method initializes the Member's schedule and returns it as a List of
     * Day
     *
     * @return The Member's schedule as a list of Day
     */
    List initializeSchedule() {
        String[] slot0 = new String[8];
        String[] slot1 = new String[8];
        String[] slot2 = new String[8];
        String[] slot3 = new String[8];
        String[] slot4 = new String[8];
        String[] slot5 = new String[8];
        String[] slot6 = new String[8];

        slot0[0] = "08-10";
        slot1[0] = "10-12";
        slot2[0] = "12-14";
        slot3[0] = "14-16";
        slot4[0] = "16-18";
        slot5[0] = "18-20";
        slot6[0] = "20-22";

        int slot;
        for (int i = 0; i < MembersActivities.size(); i++) {
            slot = findSlot(MembersActivities.get(i));
            switch (slot) {
                case 0:
                    slot0[findDay(MembersActivities.get(i))] = MembersActivities.get(i).getName();
                    break;
                case 1:
                    slot1[findDay(MembersActivities.get(i))] = MembersActivities.get(i).getName();
                    break;
                case 2:
                    slot2[findDay(MembersActivities.get(i))] = MembersActivities.get(i).getName();
                    break;
                case 3:
                    slot3[findDay(MembersActivities.get(i))] = MembersActivities.get(i).getName();
                    break;
                case 4:
                    slot4[findDay(MembersActivities.get(i))] = MembersActivities.get(i).getName();
                    break;
                case 5:
                    slot5[findDay(MembersActivities.get(i))] = MembersActivities.get(i).getName();
                    break;
                case 6:
                    slot6[findDay(MembersActivities.get(i))] = MembersActivities.get(i).getName();
            }

        }

        memberSchedule.add(new Day(slot0));
        memberSchedule.add(new Day(slot1));
        memberSchedule.add(new Day(slot2));
        memberSchedule.add(new Day(slot3));
        memberSchedule.add(new Day(slot4));
        memberSchedule.add(new Day(slot5));
        memberSchedule.add(new Day(slot6));

        return memberSchedule;

    }

    /**
     * This method returns the Member's activities
     *
     * @return List of Activities
     */
    public List<Activity> getMembersActivities() {
        return MembersActivities;
    }

    /**
     * This method sets the Member's activities
     *
     * @param MembersActivities
     */
    public void setMembersActivities(List<Activity> MembersActivities) {
        this.MembersActivities = MembersActivities;
    }

}
