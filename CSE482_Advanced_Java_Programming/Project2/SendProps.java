package secondadvancedproject;

public class SendProps {

   private boolean enable;
   private String port;
   private String password;
   private String user;
   private boolean auth;
   private String host;

   public SendProps(String[] s) {
      for (int i = 0; i < s.length; i++) {
         if (s[i].contains("enable")) {
            if (s[i].contains("true")) {
               this.enable = true;
            }
         } else if (s[i].contains("port")) {
            this.port = s[i].split("=")[1];
         } else if (s[i].contains("password")) {
            this.password = s[i].split("=")[1];
         } else if (s[i].contains("user")) {
            this.user = s[i].split("=")[1];
         } else if (s[i].contains("auth")) {
            if (s[i].contains("true")) {
               this.auth = true;
            }
         } else if (s[i].contains("host")) {
            this.host = s[i].split("=")[1];
         } else {
            System.out.println(s[i]);
         }
      }
   }

   public boolean isEnable() {
      return enable;
   }

   public boolean isAuth() {
      return auth;
   }

   public String getUser() {
      return user;
   }

   public String getPort() {
      return port;
   }

   public String getPassword() {
      return password;
   }

   public String getHost() {
      return host;
   }

}
