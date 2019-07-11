/**
 *
 * @author Emine
 */
public class Password {
    private String pas;
    private int attemts = 0;
    private boolean mustChange= false;
    public Password(String p) {
        if(isValid(p)) pas = p;
        else{
            pas = "123456";
            System.out.println("Your password is "+pas+"\nYou have to change it at first login");
            mustChange = true;
        }
    }
    public boolean login(String x){
        return pas.equals(x);
    }
    private boolean isValid(String p){
        int dCount=0, lCount=0;
        if(p.length()!=8) return false;
        for(int i=0;i<p.length();i++){
            char x = p.charAt(i);
            if(Character.isDigit(x)) dCount++;
            else if(Character.isLetter(x)) lCount++;
        }
        if(dCount==0) return false;
        if(lCount==0) return false;
        
        for(int i=0;i<p.length()-1;i++){
            for(int j=i+1;j<p.length();j++){
                if(p.charAt(i)==p.charAt(j)) return false;                
            }
        }
        return true;        
    }
    
   
}
