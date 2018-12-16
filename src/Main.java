import java.util.*;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String args[]) throws Exception{
//        int blockQueueSize = 10;
        int x = 0, y = 0, z = 0;
        String urldb = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
        String login = "mysql";
        String password = "mysql";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(urldb, login, password);
        ArrayList<Urllist> urllist = new ArrayList<>();
        ArrayList<Statlist> statlist = new ArrayList<>();
        ArrayList<String> alstr = new ArrayList<>();
        ArrayList<Future> futures = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new java.util.Date());

//        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(blockQueueSize);
//        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
//        ExecutorService service =  new ThreadPoolExecutor(numOfThread, numOfThread,
//                0L, TimeUnit.MILLISECONDS, blockingQueue, rejectedExecutionHandler);

        Statement st = con.createStatement();
        for(int i = 0; i < 10; i++) {
//            new SelThread("seltype" + i, x, date, urllist, con);
            ResultSet rs = st.executeQuery("SELECT * FROM urllistg WHERE date < '" + date + "' or date is null");
            while (rs.next()){
                urllist.add(new Urllist(rs.getInt("id"), rs.getString("url")));
            }
        }
        st.close();
        con.close();
//        Thread.sleep(10000);
        System.out.println(urllist.size());
//        for (int i = 0; i < urllist.size(); i++){
//            System.out.println("id = " + urllist.get(i).id + ", url = " + urllist.get(i).url);
//        }
//        urllist.add(new Urllist(10,"http://google.com"));
//        urllist.add(new Urllist(11,"http://rumbler.com"));
//        urllist.add(new Urllist(12,"http://yandex.ru"));
//        urllist.add(new Urllist(13,"http://mail.ru"));

        ExecutorService service = Executors.newCachedThreadPool();
//        service.submit(new SelThread("seltype" + 1, 0, date, urllist, con));

        while(!urllist.isEmpty()){
            futures.add(service.submit(new UrlThread("urltype", y, urllist, statlist, alstr)));
//            urllist.remove(0);
        }
//        System.out.println(futures.size());

//        for(int i = 0; i < 10; i++) {
//            if (!urllist.isEmpty()) {
//                new UrlThread("urltype" + i, i, urllist, statlist);
//            }
//        }
//        Thread.sleep(6000);
        boolean fin = false;
        while (!fin) {
//            System.out.println(fin);
            fin = true;
            for (int i = 0; i < futures.size(); i++) {
                fin = fin && futures.get(i).isDone();
            }
        }
//        System.out.println(futures.size());
        service.shutdown();
//        System.out.println(futures.size());
        System.out.println(urllist.size());
        System.out.println(statlist.size() + ", " + alstr.size());
//        for (int i = 0; i < statlist.size(); i++){
//            System.out.println("id = " + statlist.get(i).id + ", stat = " + statlist.get(i).stat);
//        }
        Connection coni = DriverManager.getConnection(urldb, login, password);
        while (!statlist.isEmpty()){
            int stat = statlist.get(0).stat;
            int id = statlist.get(0).id;
            statlist.remove(0);

            Statement sti = coni.createStatement();
            sti.executeUpdate("UPDATE urllistg SET date = '" + date + "', status = " + stat + " WHERE id = " + id);
            sti.close();

        }
        coni.close();
//        while (!statlist.isEmpty()){
//            service.submit(new UpdThread("updtype", z, date, statlist));
//        }

//        Thread.sleep(1000);
        System.out.println(urllist.size());
        System.out.println(statlist.size());
//        for (int i = 0; i < c; i++) {
//            System.out.println(statlist.get(i).id + ", " + statlist.get(i).stat);
//        }


//        Prepare prep = new Prepare();
//        int maxid = prep.prepare("20181215");
//        StatCont sc = new StatCont(maxid);
//        UrlCont uc = new UrlCont(maxid);
//        new UrlThread("1",1,"http:google.com",1,sc);
//        System.out.println("");
    }
}
