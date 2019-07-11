
import java.util.ArrayList;

public class Course {
    private String code, name;
    private int quota, credit;
    private ArrayList<Student> sts;

    public Course(String code, String name, int quota, int credit) {
        this.code = code;
        this.name = name;
        this.quota = quota;        
        this.credit = credit;
        sts = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public int getCredit() {
        return credit;
    }

    public String getName() {
        return name;
    }

    public int getQuota() {
        return quota;
    }
    
    public void increaseQuota(){
        quota++;
    }
                  
    public boolean addStudent(Student s){
        if(sts.size()<quota){
            if(!sts.contains(s)){           
                sts.add(s);
                return true;
            }
            else{
                System.out.println("You already took this course");
                return false;
            }
        }
        System.out.println("There is no empty seats in this course, see your quota manager");
        return false;
    }             

    public boolean removeStudent(Student s){
        if(sts.contains(s)){
            sts.remove(s);
            return true;
        }
        System.out.println("You are not taking this course, you cannot drop it.");
        return false;
    }
    public void displayStudentList(){
        System.out.println(code+" "+name+" student List");
        for(Student s: sts)
            System.out.println(s.toString());
    }
    public boolean checkStudent(Student s){
        return sts.contains(s);
    }
        
}
