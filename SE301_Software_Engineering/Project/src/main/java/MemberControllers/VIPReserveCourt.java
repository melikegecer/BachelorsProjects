package MemberControllers;

import LoginController.UserLogin;
import Repository.RepositoryFacade;
import UserManagement.Court;
import UserManagement.VIPMember;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "dtVIPReserveCourt")
public class VIPReserveCourt {

    private List<Court> AllCourts = new ArrayList<>();
    private String SelectedCourtName;
    private List<Court> SelectedCourt = new ArrayList<>();
    private List<Day> SelectedCourtSchedule = new ArrayList<>();
    RepositoryFacade rp = new RepositoryFacade();

    private String SelectedDay;
    private String SelectedSlot;

    /**
     * This method returns all courts
     *
     * @return All Courts
     */
    public List<Court> getAllCourts() {
        AllCourts = rp.getAllCourts();
        return AllCourts;
    }

    /**
     * This method returns a certain court schedule
     *
     * @return Selected Court Schedule
     * @throws ParseException
     */
    public List<Day> getCourtSchedule() throws ParseException {
        SelectedCourt = rp.getACourtSchedule(new Court(0, SelectedCourtName, "")); //getACourtSchedule courtname or courtid??
        SelectedCourtSchedule = initializeCourtSchedule();
        return SelectedCourtSchedule;
    }

    /**
     * This method initializes the selected court schedule
     *
     * @return The Selected Court Schedule
     * @throws ParseException
     */
    List initializeCourtSchedule() throws ParseException {

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
        for (int i = 0; i < SelectedCourt.size(); i++) {
            slot = findSlot(SelectedCourt.get(i));
            switch (slot) {
                case 0:
                    slot0[parseDates(SelectedCourt.get(i))] = "RESERVED";
                    break;
                case 1:
                    slot1[parseDates(SelectedCourt.get(i))] = "RESERVED";
                    break;
                case 2:
                    slot2[parseDates(SelectedCourt.get(i))] = "RESERVED";
                    break;
                case 3:
                    slot3[parseDates(SelectedCourt.get(i))] = "RESERVED";
                    break;
                case 4:
                    slot4[parseDates(SelectedCourt.get(i))] = "RESERVED";
                    break;
                case 5:
                    slot5[parseDates(SelectedCourt.get(i))] = "RESERVED";
                    break;
                case 6:
                    slot6[parseDates(SelectedCourt.get(i))] = "RESERVED";
            }

        }

        SelectedCourtSchedule.add(new Day(slot0));
        SelectedCourtSchedule.add(new Day(slot1));
        SelectedCourtSchedule.add(new Day(slot2));
        SelectedCourtSchedule.add(new Day(slot3));
        SelectedCourtSchedule.add(new Day(slot4));
        SelectedCourtSchedule.add(new Day(slot5));
        SelectedCourtSchedule.add(new Day(slot6));

        return SelectedCourtSchedule;

    }

    /**
     * This method parses the date of a given court slot and returns the day of
     * the week
     *
     * @param court
     * @return The Day of the Week
     * @throws ParseException
     */
    int parseDates(Court court) throws ParseException {
        String slot = court.getSlot();
        String[] parts = slot.split("-");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];
        String inputDate = day + "." + month + "." + year;
        SimpleDateFormat newFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = newFormat.parse(inputDate);
        DateFormat format2 = new SimpleDateFormat("EEEE");
        String nameOfTheDay = format2.format(date);
        switch (nameOfTheDay) {
            case "Pazartesi":
                return 1;
            case "Salý":
                return 2;
            case "Çarþamba":
                return 3;
            case "Perþembe":
                return 4;
            case "Cuma":
                return 5;
            case "Cumartesi":
                return 6;
            case "Pazar":
                return 7;
        }
        return -1;

    }

    /**
     * This method returns the integer value of the slot of a given court slot
     *
     * @param court
     * @return Slot
     */
    int findSlot(Court court) {
        String slot = court.getSlot();
        String[] parts = slot.split("-");
        String start = parts[3];
        String end = parts[4];
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
     * This method returns the selected court name
     *
     * @return The Selected Court Name
     */
    public String getSelectedCourtName() {
        return SelectedCourtName;
    }

    /**
     * This method sets the selected court name
     *
     * @param Selected
     */
    public void setSelectedCourtName(String Selected) {
        this.SelectedCourtName = Selected;
    }

    /**
     * This method returns the selected day
     *
     * @return The Selected Day
     */
    public String getSelectedDay() {
        return SelectedDay;
    }

    /**
     * This method sets the selected day
     *
     * @param SelectedDay
     */
    public void setSelectedDay(String SelectedDay) {
        this.SelectedDay = SelectedDay;
    }

    /**
     * This method returns the selected slot
     *
     * @return The Selected Slot
     */
    public String getSelectedSlot() {
        return SelectedSlot;
    }

    /**
     * This method sets the selected slot
     *
     * @param SelectedSlot
     */
    public void setSelectedSlot(String SelectedSlot) {
        this.SelectedSlot = SelectedSlot;
    }

    /**
     * This method returns the selected court
     *
     * @return The Selected Court
     */
    public List<Court> getSelectedCourt() {
        return SelectedCourt;
    }

    /**
     * This method sets the selected court
     *
     * @param SelectedCourt
     */
    public void setSelectedCourt(List<Court> SelectedCourt) {
        this.SelectedCourt = SelectedCourt;
    }

    /**
     * This method reserves the selected court
     *
     * @pre getSelectedCourt() -> forAll (court|
     * !court.getSlot().equals(getSelectedSlot()))
     * @post getSelectedCourt() -> includes (court|
     * court.getSlot().equals(getSelectedSlot()))
     */
    public void reserveTheCourt() {
        boolean approve = true;
        boolean reserveApprove = false;
        switch (SelectedDay) {
            case "Monday":
                SelectedDay = "Mo";
            case "Tuesday":
                SelectedDay = "Tu";
            case "Wednesday":
                SelectedDay = "We";
            case "Thursday":
                SelectedDay = "Th";
            case "Friday":
                SelectedDay = "Fr";
            case "Saturday":
                SelectedDay = "Sa";
            case "Sunday":
                SelectedDay = "Su";
        }
        SelectedSlot = SelectedDay + "-" + SelectedSlot;
        for (int i = 0; i < getSelectedCourt().size(); i++) {
            if (getSelectedCourt().get(i).getSlot().equals(SelectedSlot)) {
                approve = false;
            }
        }

        if (approve) {
            VIPMember vip = rp.getVipMember(UserLogin.getUserLoggedIn());
            Court court = new Court(0, SelectedCourtName, SelectedDay + "-" + SelectedSlot);
            rp.vipmemberReservesCourt(vip, court);
            for (int i = 0; i < getSelectedCourt().size(); i++) {
                if (getSelectedCourt().get(i).getSlot().equals(SelectedSlot)) {
                    reserveApprove = true;
                }
            }
            if (reserveApprove) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Completed", "You reserved the court.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Transaction has failed.");
                RequestContext.getCurrentInstance().showMessageInDialog(message);
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Transaction has failed.");
            RequestContext.getCurrentInstance().showMessageInDialog(message);
        }

    }

}
