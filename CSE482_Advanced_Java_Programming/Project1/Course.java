package firstadvancedproject;

public class Course {

   private String deptName;
   private String level;
   private String courseName;
   private String day;
   private String hour;

   public Course(String deptName, String level, String courseName, String day, String hour) {
      this.deptName = deptName;
      this.level = level;
      this.courseName = courseName;
      this.day = day;
      this.hour = hour;
   }

   public String getCourseName() {
      return courseName;
   }

   public String getDay() {
      return day;
   }

   public String getDeptName() {
      return deptName;
   }

   public String getHour() {
      return hour;
   }

   public String getLevel() {
      return level;
   }

}
