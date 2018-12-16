import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UrlThread implements Runnable{
    Thread thread;
    private String name;
    private String url;
    private int id;
    private int num;
    private ArrayList<Urllist> urllist;
    private ArrayList<Statlist> statlist;
    private ArrayList<String> alstr;
    UrlThread(String name, int num, ArrayList<Urllist> urllist, ArrayList<Statlist> statlist, ArrayList<String> alstr){
//        super(name);
        this.name = name;
        this.url = urllist.get(0).url;
        this.id = urllist.get(0).id;
        urllist.remove(0);
        this.num = num;
        this.statlist = statlist;
        this.alstr = alstr;
        thread = new Thread(this);
//        thread.start();
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
            statlist.add(new Statlist(id, hpCon.getResponseCode()));
//            System.out.println("id = " + id + ", stat = " + hpCon.getResponseCode());
//          Connection con = DriverManager.getConnection(urldb, login, password);
//          Statement st = con.createStatement();
//          st.executeUpdate("UPDATE urllistg SET date = '" + date + "', status = " + hpCon.getResponseCode() + " WHERE id = " + id);
//          st.close();
        }catch (UnknownHostException uhe){
            statlist.add(new Statlist(id, 600));
//            System.out.println("600");
        }catch(Exception e){
            System.out.println(e);
        }finally{
            alstr.add(" ");
        }

    }
}
