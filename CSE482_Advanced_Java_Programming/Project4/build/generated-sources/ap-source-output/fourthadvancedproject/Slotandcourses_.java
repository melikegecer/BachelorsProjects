package fourthadvancedproject;

import fourthadvancedproject.Course;
import fourthadvancedproject.Slot;
import fourthadvancedproject.SlotandcoursesPK;
import fourthadvancedproject.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-04T23:35:15")
@StaticMetamodel(Slotandcourses.class)
public class Slotandcourses_ { 

    public static volatile SingularAttribute<Slotandcourses, Student> student;
    public static volatile SingularAttribute<Slotandcourses, String> grade;
    public static volatile SingularAttribute<Slotandcourses, Course> course;
    public static volatile SingularAttribute<Slotandcourses, Slot> slot;
    public static volatile SingularAttribute<Slotandcourses, SlotandcoursesPK> slotandcoursesPK;

}