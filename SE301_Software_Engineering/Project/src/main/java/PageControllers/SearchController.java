package PageControllers;

import ActivityManagement.Activity;
import Repository.RepositoryFacade;
import UserManagement.Instructor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class SearchController {

    private List<Activity> activityList;
    private List<Instructor> instructorList;
    
    /**
     * valueToSearch should not be null
     * if null searches nothing, otherwise shows related information
     * @invariant valueToSearch != null
     */
    private String valueToSearch;
    private RepositoryFacade rf = new RepositoryFacade();

    /**
     *
     * @return the value to be searched in activity and instructor lists
     */
    public String getValueToSearch() {
        return valueToSearch;
    }

    /**
     * sets the value to be searched
     *
     * @param valueToSearch
     */
    public void setValueToSearch(String valueToSearch) {
        this.valueToSearch = valueToSearch;
    }

    /**
     *
     * @return activity list to show
     */
    public List<Activity> getActivityList() {
        activityList = new ArrayList<>();
        ArrayList<Activity> a = rf.getAllActivities();
        for (Activity a1 : a) {
            if (a1.getName().toLowerCase().equals(valueToSearch)) {
                activityList.add(a1);
            }
        }
        return activityList;
    }

    /**
     *
     * @return instructor list to show
     */
    public List<Instructor> getInstructorList() {
        instructorList = new ArrayList<>();
        ArrayList<Instructor> i = rf.getAllInstructors();
        for (Instructor i1: i) {
            if (i1.getName().toLowerCase().equals(valueToSearch) || i1.getSurName().toLowerCase().equals(valueToSearch)) {
                instructorList.add(i1);
            }
        }
        return instructorList;
    }

}
