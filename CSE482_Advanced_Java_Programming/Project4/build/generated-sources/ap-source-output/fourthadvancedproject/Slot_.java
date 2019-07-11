package fourthadvancedproject;

import fourthadvancedproject.Slotandcourses;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-04T23:35:15")
@StaticMetamodel(Slot.class)
public class Slot_ { 

    public static volatile SingularAttribute<Slot, String> slotname;
    public static volatile SingularAttribute<Slot, String> slotcode;
    public static volatile SingularAttribute<Slot, Integer> semester;
    public static volatile SingularAttribute<Slot, Integer> credit;
    public static volatile CollectionAttribute<Slot, Slotandcourses> slotandcoursesCollection;

}