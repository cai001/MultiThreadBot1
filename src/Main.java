import java.sql.*;
public class Main {
    public static void main(String args[]) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Prepare prep = new Prepare();
        int maxid = prep.prepare("20181215");
        StatCont sc = new StatCont(maxid);
        UrlCont uc = new UrlCont(maxid);
//        new UrlThread("1",1,"http:google.com",1,sc);
        System.out.println("");
    }
}
