package PageControllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean
public class PageTraveller {

    private String mainSelectedPage = "Main";
    private String schedulerSelectedPage = "Main";
    private String memberSelectedPage = "Main";
    private String vipmemberSelectedPage = "Main";
    private String registrarSelectedPage = "Main";
    private String instructorSelectedPage = "Main";
    private boolean activity;

    /**
     * in order to refresh form in interface, it needed to check if selected page is an activity page or not
     * @return if selected page is an activity or not
     */
    public boolean isActivity() {
        if (mainSelectedPage.equals("Instructors") || mainSelectedPage.equals("Contact") || mainSelectedPage.equals("Preregister") || mainSelectedPage.equals("Search")) {
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @return instructor s selected page
     */
    public String getInstructorSelectedPage() {
        return instructorSelectedPage;
    }

    /**
     * 
     * @return member s selected page
     */
    public String getMemberSelectedPage() {
        return memberSelectedPage;
    }

    /**
     * sets the instructor s selected page
     * @param instructorSelectedPage 
     */
    public void setInstructorSelectedPage(String instructorSelectedPage) {
        this.instructorSelectedPage = instructorSelectedPage;
    }

    /**
     * sets the member s selected page
     * @param memberSelectedPage 
     */
    public void setMemberSelectedPage(String memberSelectedPage) {
        this.memberSelectedPage = memberSelectedPage;
    }

    /**
     * sets the registrar s selected page
     * @param registrarSelectedPage 
     */
    public void setRegistrarSelectedPage(String registrarSelectedPage) {
        this.registrarSelectedPage = registrarSelectedPage;
    }

    /**
     * sets the vip member s selected page
     * @param vipmemberSelectedPage 
     */
    public void setVipmemberSelectedPage(String vipmemberSelectedPage) {
        this.vipmemberSelectedPage = vipmemberSelectedPage;
    }

    /**
     * 
     * @return registrar s selected page
     */
    public String getRegistrarSelectedPage() {
        return registrarSelectedPage;
    }

    /**
     * 
     * @return vip member s selected page
     */
    public String getVipmemberSelectedPage() {
        return vipmemberSelectedPage;
    }

    /**
     * 
     * @return main menu s selected page
     */
    public String getMainSelectedPage() {
        return mainSelectedPage;
    }

    /**
     * sets the main menu s selected page
     * @param mainSelectedPage 
     */
    public void setMainSelectedPage(String mainSelectedPage) {
        this.mainSelectedPage = mainSelectedPage;
    }

    /**
     * sets the scheduler s selected page
     * @param schedulerSelectedPage 
     */
    public void setSchedulerSelectedPage(String schedulerSelectedPage) {
        this.schedulerSelectedPage = schedulerSelectedPage;
    }

    /**
     * 
     * @return scheduler s selected page
     */
    public String getSchedulerSelectedPage() {
        return schedulerSelectedPage;
    }

    /**
     * 
     * @return main menu s selected page 
     */
    public String getMainPageInfo() {
        return fileRead(mainSelectedPage);
    }
    
    /**
     * reads the related txt of a page
     * @param pageName
     * @return text of the page to show
     */
    private String fileRead(String pageName) {
        String s = "";
        String x = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getRealPath("/Interfaces/");
        int start = x.indexOf("GEMSTrial");
        x = x.substring(0, start + 9);
        x += "\\src\\main\\webapp\\Interfaces\\ActivityInfos\\" + pageName + ".txt";

        try {
            File f = new File(x);
            Scanner scn = new Scanner(f);
            while (scn.hasNextLine()) {
                s += scn.nextLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Exception " + ex.getMessage());
        }
        return s;
    }
}
