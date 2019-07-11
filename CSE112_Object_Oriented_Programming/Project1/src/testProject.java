
import java.util.ArrayList;
import java.util.Scanner;

public class testProject {
    private static Scanner scn = new Scanner(System.in);
    private  static ArrayList<Student> allStudents;
    public static ArrayList<Course> allCourses;
    public static void main(String[] argv){
        allStudents = new ArrayList<>();
        allCourses = new ArrayList<>();
        String r = displayMenu1();
        while(!r.equals("q")){
            if(r.equals("s"))
                studentMenu();
            else if(r.equals("m"))
                managerMenu();
            else
                System.out.println("invalid choice");
            r = displayMenu1();
        }
    }
    public static String displayMenu1(){
        System.out.println();
        System.out.println("to continue as Student: Press S");
        System.out.println("to continue as Manager: Press M");
        System.out.println("to Quit: Press Q");
        System.out.print("Choice:   ");
        String c = scn.next();        
        return c.toLowerCase();
    }
    public static void studentMenu(){
        String c="";
        do{
            System.out.println();
            System.out.println("to Register: Press R");
            System.out.println("to Login, and select courses: Press L");
            System.out.println("to return to main menu: Press Q");
            System.out.print("Choice:   ");
            c = scn.next().toLowerCase();        
            if(c.equals("r"))
                registrationProcess();
            else if(c.equals("l"))
                courseProcesses();
        }
        while(!c.equals("q"));
        
    }
    public static void courseProcesses(){
        int index = login();
        if(index!=-1){
            String c="";
            do{
                System.out.println();
                System.out.println("Add a course: A");
                System.out.println("Drop a course: D");
                System.out.println("List my courses:L  ");
                System.out.println("Quit : Q");
                c = scn.next().toLowerCase();
                switch(c){
                    case "a": addCourse(index); break;
                    case "d":dropCourse(index); break;
                    case "l": listCourses(index); break;
                }
            }while(!c.equals("q"));
        }
        else{
            System.out.println("something went wrong");
        }
    }
    private static void listCourses(int index){
        allStudents.get(index).listMyCourses();
    }
    private static void dropCourse(int index){
        System.out.println();
        System.out.print("Enter course code: ");
        String code = scn.next().toLowerCase();
        for(int i=0;i<allCourses.size();i++){
            if(allCourses.get(i).getCode().equals(code)){
                allStudents.get(index).dropCourse(allCourses.get(i));
            }
        } 
    }
    private static void addCourse(int index){
        System.out.println();
        System.out.print("Enter course code: ");
        String code = scn.next().toLowerCase();
        for(int i=0;i<allCourses.size();i++){
            if(allCourses.get(i).getCode().equals(code)){
                allStudents.get(index).addCourse(allCourses.get(i));
            }
        }            
    }
    public static int login(){
        System.out.println();
        System.out.print("Enter your id: ");
        int id = scn.nextInt();
        System.out.print("Enter your password: ");
        String pas = scn.next();
        for(int i=0;i<allStudents.size();i++){
            if(allStudents.get(i).getId()==id){
                if(allStudents.get(i).login(pas))
                    return i;
            }
        }
        return -1;
    }
    public static void registrationProcess(){
        System.out.println();
        System.out.print("Enter your name: ");
        String name = scn.next().toLowerCase();
        System.out.print("Enter your password: ");
        String pas = scn.next();
        Student s = new Student(name, pas);
        allStudents.add(s);
    }
    public static void managerMenu(){
        String c="";        
        do{
            System.out.println();
            System.out.println("to add a course: press A");
            System.out.println("to increase quota: press I");
            System.out.println("to view students of a course: press V");
            System.out.println("to quit press Q");
            c = scn.next().toLowerCase();
            switch(c){
                case "a": manAddCourse();break;
                case "i": manIncQuota();break;
                case "v": manView();break;
            }
        }
        while(!c.equals("q"));
    }
    private static void manAddCourse(){
        System.out.println();
        System.out.println("Enter code, name, credit, quota");
        String code = scn.next().toLowerCase();
        String name = scn.next().toLowerCase();
        int credit = scn.nextInt();
        int quota = scn.nextInt();
        Course c = new Course(code, name, quota, credit);
        allCourses.add(c);        
    }
    private static void manIncQuota(){
        System.out.println();
        System.out.print("Enter code");
        String code = scn.next().toLowerCase();
        for(int i=0;i<allCourses.size();i++){
            if(allCourses.get(i).getCode().equals(code))
                allCourses.get(i).increaseQuota();
        }
    }
    private static void manView(){
        System.out.println();
        System.out.print("Enter code");
        String code = scn.next().toLowerCase();
        for(int i=0;i<allCourses.size();i++){
            if(allCourses.get(i).getCode().equals(code))
                allCourses.get(i).displayStudentList();
        }
    }            
}

