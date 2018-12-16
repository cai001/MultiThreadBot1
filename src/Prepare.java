import java.sql.*;
public class Prepare {
    public int prepare (String date) throws Exception{
        String urldb = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
        String login = "mysql";
        String password = "mysql";
        Connection con = DriverManager.getConnection(urldb, login, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM urllist.urllistg WHERE date < '" + date + "' or date is null");
        rs.next();
        int count = rs.getInt(1);
        ResultSet rs2 = st.executeQuery("SELECT MAX(id) FROM urllist.urllistg WHERE date < '" + date + "' or date is null");
        rs2.next();
        int maxid = rs.getInt(1);
        st.close();

        return maxid;
    }
}
