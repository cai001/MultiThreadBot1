import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UrlThread extends Thread{
    private String name;
    private String url;
    private int id;
    private int num;
    private StatCont sc;
    UrlThread(String name, int num, String url, int id, StatCont sc){
        super(name);
        this.name = name;
        this.url = url;
        this.id = id;
        this.num = num;
        this.sc = sc;
        start();
    }
//    private String urldb = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
//    private String login = "mysql";
//    private String password = "mysql";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    private String date = sdf.format(new java.util.Date());
    public void run(){
        try{
            URL hp = new URL(url);
            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
            sc.setStat(this.id, hpCon.getResponseCode());
//            Connection con = DriverManager.getConnection(urldb, login, password);
//            Statement st = con.createStatement();
//            st.executeUpdate("UPDATE urllistg SET date = '" + date + "', status = " + hpCon.getResponseCode() + " WHERE id = " + id);
//            st.close();
        }catch (UnknownHostException uhe){
            sc.setStat(this.id, 600);
        }catch(Exception e){
            System.out.println(e);
        }finally{

        }
    }
}
