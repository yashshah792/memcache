/*
#Yash Shah
#1001111714
#CSE 6331 Cloud Computing
#Assignment-2
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// References: https://www.linkedin.com/groups/Storing-MySQL-result-set-in-3025052.S.141798929
//http://dev.mysql.com/doc/refman/5.6/en/ha-memcached-interfaces-java.html
//https://rqureshi.wordpress.com/2010/05/15/how-to-serialize-resultset-in-java/
//http://undocumentedmatlab.com/blog/inter-matlab-data-transfer-with-memcached
//https://github.com/gwhalin/Memcached-Java-Client/tree/performance
package amazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.HashMap;

import com.meetup.memcached.MemcachedClient;
import com.meetup.memcached.SockIOPool;
import com.sun.rowset.CachedRowSetImpl; 
import java.util.Date;

/**
 *
 * @author yashshah792
 */
public class Amazon {

    public static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static String DB_URL = "******";
    public static Connection conn;
    public static Statement smt;
    public static String USER = "*****";
    public static String PASS = "*****";
        
    public static void connectionfunt()
    {
        long end_time, start_time,total_time = 0;
        int i;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            for(i=0;i<500;i++)
            {
                Random rm = new Random();
                int z;
                //System.out.println("Random number generation pehla");
                int n = rm.nextInt(90);
                int m = 0 + (n%(90 - n));
                if (n == m)
                    {
                        m = 2 + (n%(90 - n));
                    }
                else if(n > m)
                {
                    z = n; n = m; m = z; z = 0;
                }
                int m1 = rm.nextInt(180);
                int n1 = -1 * rm.nextInt(180);
                int n2 = rm.nextInt(25);
                int m2 = 0 + (n2 %(25 - n2));
                if (n2 == m2)
                    {
                        m2 = 2 + (n2%(90 - n2));
                    }
                else if (n2 > m2)
                {
                    z = n2; n2 = m2; m2 = z; z = 0;
                }
                int n3 = rm.nextInt(30);
                int m3 = 0 + (n3 %(30 - n3));
                if (n3 == m3)
                    {
                        m3 = 2 + (n3%(90 - n3));
                    }
                else if(n3 > m3)
                {
                    z = n3; n3 = m3; m3 = z; z = 0;
                }
                System.out.println(n+" "+m+" "+n1+" "+m2+" "+n2+" "+m2+" "+n3+" "+m3);
                start_time = System.currentTimeMillis();
                smt = conn.createStatement();
                String query = "SELECT * FROM project2 WHERE latitude BETWEEN "+n+" AND "+m+";";
                ResultSet rs = smt.executeQuery(query);
                
                String query1 = "SELECT * FROM project2 WHERE longitude BETWEEN "+n1+" AND "+m1+";";
                ResultSet rs1 = smt.executeQuery(query1);
                
                String query2 = "SELECT * FROM project2 WHERE depth BETWEEN "+n2+" AND "+m2+";";
                ResultSet rs2 = smt.executeQuery(query2);
                
                String query3 = "SELECT * FROM project2 WHERE net BETWEEN "+n3+" AND "+m3+";";
                ResultSet rs3 = smt.executeQuery(query3);

                end_time =  System.currentTimeMillis();
                total_time = total_time + (end_time - start_time);
                end_time = 0;
                start_time = 0;
                System.out.println("Count"+i);
            }
            System.out.println("Time Taken : "+(total_time/1000)+ " seconds");
            smt.close();
        } catch (Exception e) {
            System.err.println("Got An Exception " + e.getMessage()+"  "+e.getLocalizedMessage()+" "+e.getStackTrace());
        }        
    }
    
    public static void randomquery()
    {
        long end_time, start_time,total_time = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            for(int i = 0; i < 334; i++){
            Random rm = new Random();
            double lat = 20 + (rm.nextDouble()%(90));
            double lon = -180 + (rm.nextDouble()%(360));
            float de = 1 + (rm.nextFloat()%23);
            float ma = 0 + (rm.nextFloat()%2);
            String a[] = {"nc","ci","hv","uw","us","pr","ak","uu","nn","nm","se","mb"};
            int sel = rm.nextInt(12);
            int gap = 40 + (rm.nextInt(90)%50);
            int ns = rm.nextInt(30);
            smt = conn.createStatement();
            
            start_time = System.currentTimeMillis();
            String query = "SELECT * FROM project2 WHERE latitude="+lat;
            ResultSet rs = smt.executeQuery(query);
            String query1 = "SELECT * FROM project2 WHERE longitude="+lon;
            ResultSet rs1 = smt.executeQuery(query1);
            String query2 = "SELECT * FROM project2 WHERE depth="+de;
            ResultSet rs2 = smt.executeQuery(query2);
            String query3 = "SELECT * FROM project2 WHERE mag="+ma;
            ResultSet rs3 = smt.executeQuery(query3);
            String query4 = "SELECT * FROM project2 WHERE net='"+a[sel]+"'";
            ResultSet rs4 = smt.executeQuery(query4);
            String query5 = "SELECT * FROM project2 WHERE gap="+gap;
            ResultSet rs5 = smt.executeQuery(query5);
            String query6 = "SELECT * FROM project2 WHERE nst="+ns;
            ResultSet rs6 = smt.executeQuery(query6);
            
            end_time =  System.currentTimeMillis();
            total_time = total_time + (end_time - start_time);
            end_time = 0;
            start_time = 0;
            System.out.print(i);
            }
            System.out.println("Time Taken : "+(total_time/1000)+ " seconds");
            smt.close();
        }
        catch(Exception e){
            System.err.println("Got An Exception " + e.getMessage()+"  "+e.getLocalizedMessage()+" "+e.getStackTrace());
        }
    }
    
    public static void memrandomfunt()
    {
        Integer[] m = {1};
        String[] servers = {"127.0.0.1:11211"};
        SockIOPool pool = SockIOPool.getInstance("Test1");
        pool.setServers( servers );
        pool.setWeights(m);
        pool.setFailover( true );
        pool.setInitConn( 5 );
        pool.setMinConn( 5 );
        pool.setMaxConn( 2160000 );               
        pool.initialize();
        MemcachedClient mcc = new MemcachedClient();
        long end_time, start_time,total_time = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Random rm = new Random();
            
            for(int i=0;i<400;i++){
            double lat = 20 + (rm.nextDouble()%(20));
            float de = 1 + (rm.nextFloat()%23);
            smt = conn.createStatement();
            
            if((mcc.get(Double.toString(lat)).toString()).isEmpty()){
                String query = "SELECT * FROM project2 WHERE latitude="+lat;
                ResultSet rs = smt.executeQuery(query);
                CachedRowSetImpl crsi = new CachedRowSetImpl();
                crsi.populate(rs);
                mcc.set(mcc.get(Double.toString(lat)).toString(),crsi,new Date (50*60000));
            } else {
                CachedRowSetImpl crs = (CachedRowSetImpl)mcc.get(mcc.get(Double.toString(lat)).toString());
            }
            if(mcc.get(Float.toString(de)).toString().isEmpty())
            {
               String query2 = "SELECT * FROM project2 WHERE depth="+de;
                ResultSet rs2 = smt.executeQuery(query2);
                CachedRowSetImpl crsi = new CachedRowSetImpl();
                crsi.populate(rs2);
                mcc.set(mcc.get(Double.toString(lat)).toString(),crsi,new Date (50*60000));
            }
            else{
                CachedRowSetImpl crs = (CachedRowSetImpl)mcc.get(mcc.get(Double.toString(lat)).toString());
            }
            }
        }
        catch(Exception e){
            System.err.println("Got An Exception " + e.getMessage()+"  "+e.getLocalizedMessage()+" "+e.getStackTrace());
        }
    }
    
//    public void memcontfunt(){
//        Integer[] m = {1};
//        String[] servers = {"127.0.0.1:11211"};
//        SockIOPool pool = SockIOPool.getInstance("Test1");
//        pool.setServers( servers );
//        pool.setWeights(m);
//        pool.setFailover( true );
//        pool.setInitConn( 5 );
//        pool.setMinConn( 5 );
//        pool.setMaxConn( 2160000 );               
//        pool.initialize();
//        MemcachedClient mcc = new MemcachedClient();
//        long end_time, start_time,total_time = 0;
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//            
//            for(i = 0; i<1000;i++)
//            {
//            Random rm = new Random();
//            int z;
//            int n = rm.nextInt(90);
//            int m1 = 0 + (n%(90 - n));
//            if (n == m1)
//                {
//                    m1 = 2 + (n%(90 - n));
//                }
//            else if(n > m1)
//            {
//                z = n; n = m1; m1 = z; z = 0;
//            }
//            }
//        }
//        catch(Exception e){
//            System.err.println("Got An Exception " + e.getMessage()+"  "+e.getLocalizedMessage()+" "+e.getStackTrace());
//        }
//    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //connectionfunt();
        randomquery();
        //memrandomfunt();
    }
}
