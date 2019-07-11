package secondadvancedproject;

public class ReceiveProps {

   private String password;
   private String user;
   private boolean auth;
   private String host;
   private String protocol;

   public ReceiveProps(String[] s) {
      for (int i = 0; i < s.length; i++) {
         if (s[i].contains("password")) {
            this.password = s[i].split("=")[1];
         } else if (s[i].contains("user")) {
            this.user = s[i].split("=")[1];
         } else if (s[i].contains("auth")) {
            if (s[i].contains("true")) {
               this.auth = true;
            }
         } else if (s[i].contains("host")) {
            this.host = s[i].split("=")[1];
         } else if (s[i].contains("protocol")) {
            this.protocol = s[i].split("=")[1];
         } else {
            System.out.println(s[i]);
         }
      }
   }

   public String getUser() {
      return user;
   }

   public String getPassword() {
      return password;
   }

   public String getHost() {
      return host;
   }

   public boolean isAuth() {
      return auth;
   }

   public String getProtocol() {
      return protocol;
   }
}
