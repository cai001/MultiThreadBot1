import java.net.*;
import java.util.ArrayList;
import java.sql.*;

public class UpdThread implements Runnable{
    Thread thread;
    private String name;
    private int stat;
    private int id;
    private int num;
    private String date;
    private ArrayList<Statlist> statlist;
    UpdThread(String name, int num, String date, ArrayList<Statlist> statlist){
//        super(name);
        this.name = name;
        this.stat = statlist.get(0).stat;
        this.id = statlist.get(0).id;
        statlist.remove(0);
        this.num = num;
        this.date = date;
        thread = new Thread(this);
//        start();
    }
    private String urldb = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
    private String login = "mysql";
    private String password = "mysql";
    public void run(){
        try{
//            URL hp = new URL(url);
//            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
//            statlist.add(new Statlist(id, hpCon.getResponseCode()));
            Connection con = DriverManager.getConnection(urldb, login, password);
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE urllistg SET date = '" + date + "', status = " + stat + " WHERE id = " + id);
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }finally{

        }
    }
}
