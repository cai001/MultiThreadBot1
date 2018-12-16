import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SelThread extends Thread{
    private String name;
    private String date;
    private int id;
    private int num;
    private StatCont sc;
    SelThread(String name, int num, String date, int id, StatCont sc){
        super(name);
        this.name = name;
        this.date = date;
        this.id = id;
        this.num = num;
        this.sc = sc;
        start();
    }
    private String urldb = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
    private String login = "mysql";
    private String password = "mysql";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    private String date = sdf.format(new java.util.Date());
    public void run(){
        try{
//            URL hp = new URL(url);
//            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
//            sc.setStat(this.id, hpCon.getResponseCode());
            Connection con = DriverManager.getConnection(urldb, login, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM urllist.urllistg WHERE date < '" + date + "' or date is null");
            sc.setStat(200,89);
            st.close();
        }catch(Exception e){
            System.out.println(e);
        }finally{

        }
    }
}
