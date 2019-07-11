package PageControllers;

import Repository.RepositoryFacade;
import UserManagement.Instructor;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class InstructorController {

    private List<Instructor> list;
    private RepositoryFacade rf = new RepositoryFacade();

    /**
     * gets list of instructors from database and fills the list
     */
    public void fillInstructorList() {
        list = new ArrayList<>();
        list = rf.getAllInstructors();
//        list.add(new Instructor(0, new Password(), "ksmdadl", "dkjbcn", "dsknskd", 'f', new Date(2015, 11, 11), "fhjsdk", "dvfhsjk"));
//        list.add(new Instructor(0, new Password(), "sdfgdf", "dkjbcn", "dsknskd", 'f', new Date(2015, 11, 11), "fhjsdk", "dvfhsjk"));
//        list.add(new Instructor(0, new Password(), "dfgdfg", "dkjbcn", "dsknskd", 'f', new Date(2015, 11, 11), "fhjsdk", "dvfhsjk"));
//        list.add(new Instructor(0, new Password(), "ýululýrthy", "dkjbcn", "dsknskd", 'f', new Date(2015, 11, 11), "fhjsdk", "dvfhsjk"));
//        list.add(new Instructor(0, new Password(), "fgsdggsfd", "dkjbcn", "dsknskd", 'f', new Date(2015, 11, 11), "fhjsdk", "dvfhsjk"));
    }
    
    /**
     * 
     * @return instructor list
     */
    public List<Instructor> getList() {
        fillInstructorList();
        return list;
    }

    /**
     * sets the instructor list from the database
     * @param list 
     */
    public void setList(List<Instructor> list) {
        this.list = list;
    }

}
