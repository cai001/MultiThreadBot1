import java.util.Collection;
import java.util.ArrayList;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

public class Main {
    public static void main(String args[]) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        ArrayList<Urllist> urllist = new ArrayList<>();
        ArrayList<Statlist> statlist = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());

        urllist.add(new Urllist(10,"http://google.com"));
        urllist.add(new Urllist(11,"http://rumbler.com"));
        urllist.add(new Urllist(12,"http://yandex.ru"));
        urllist.add(new Urllist(13,"http://mail.ru"));

        for(int i = 0; i < 10; i++) {
            if (!urllist.isEmpty()) {
                new UrlThread("urltype" + i, i, urllist, statlist);
            }
        }
        Thread.sleep(600);
        for(int i = 0; i < 10; i++) {
           if(!statlist.isEmpty()){
               new UpdThread("updtype" + i, i, date, statlist);
           }
        }
//        Thread.sleep(600);
//        int c = statlist.size();
//        System.out.println(c);
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
