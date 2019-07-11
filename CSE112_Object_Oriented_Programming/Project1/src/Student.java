

public class Student {
    private String name;
    private static int count;
    private int id;
    private int myCredit;
    private Password myPassword;    

    public Student(String name, String p) {
        this.name = name;
        this.id = count++;
        System.out.println("Your id is: "+ id);
        myPassword = new Password(p);
        myCredit = 19;
    }
    public boolean equals(Student s){ //since the id s are unique, it is enough to check only id s
        return this.id==s.id;        
    }
    
    public boolean addCourse(Course c){
        if(myCredit>=c.getCredit()){
            if(c.addStudent(this)){
                myCredit -=c.getCredit();
                System.out.println("You are taking the course now!");
                return true;
            }
        }
        System.out.println("You dont have enough credits to take this course. Try courses with smaller credits");
        return false;
    }
    public void dropCourse(Course c){
        if(c.removeStudent(this))
            myCredit+=c.getCredit();
    }
    
    public boolean login(String s){
        return myPassword.login(s);
    }
    public void listMyCourses(){
        for(Course c: testProject.allCourses){
            if(c.checkStudent(this))
                System.out.println(c.getCode()+" "+c.getName());
        }
    }

    public int getId() {
        return id;
    }
    public String toString(){
        
        return name+" ";
    }
    
}
