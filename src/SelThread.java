import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SelThread extends Thread{
    private String name;
    private String date;
    private int id;
    private int num;
    ArrayList<Urllist> urllist;
    Connection con;
    private StatCont sc;
    SelThread(String name, int num, String date, ArrayList<Urllist> urllist, Connection con){
        super(name);
        this.name = name;
        this.date = date;
        this.num = num;
        this.urllist = urllist;
        this.con = con;
        start();
    }
//    private String urldb = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
//    private String login = "mysql";
//    private String password = "mysql";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    private String date = sdf.format(new java.util.Date());
    public void run(){
        try{
//            URL hp = new URL(url);
//            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
//            sc.setStat(this.id, hpCon.getResponseCode());
//            Connection con = DriverManager.getConnection(urldb, login, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM urllistg WHERE date < '" + date + "' or date is null");
            while (rs.next()){
                urllist.add(new Urllist(rs.getInt("id"), rs.getString("url")));
            }
            st.close();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            num++;
        }
    }
}
