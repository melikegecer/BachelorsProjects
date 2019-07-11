package fourthadvancedproject;

import fourthadvancedproject.Slotandcourses;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-04T23:35:15")
@StaticMetamodel(Student.class)
public class Student_ { 

    public static volatile SingularAttribute<Student, Boolean> minordegree;
    public static volatile SingularAttribute<Student, String> studentnumber;
    public static volatile SingularAttribute<Student, Date> leavingdate;
    public static volatile CollectionAttribute<Student, Slotandcourses> slotandcoursesCollection;

}