package secondadvancedproject;

import java.util.Date;

public class ReceivedMails {

   private String from;
   private String subject;
   private String content;
   private Date date;

   public ReceivedMails(String from, String subject, String content, Date date) {
      this.from = from;
      this.subject = subject;
      this.content = content;
      this.date = date;
   }

   public String getContent() {
      return content;
   }

   public String getFrom() {
      return from;
   }

   public String getSubject() {
      return subject;
   }

   public Date getDate() {
      return date;
   }

   public String info() {
      return "FROM: " + from + "\n" + "SUBJECT: " + subject + "\n" + "DATE: " + date.toString() + "\n" + "CONTENT: " + content;
   }

}
