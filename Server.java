import java.sql.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;

// Server class
public class Server {
    static DatagramSocket ds;

    public static void main(String[] args) throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "****");

            // ds = new DatagramSocket(5647);
            // DatagramSocket ds = new DatagramSocket(5647);
            ServerSocket ss = new ServerSocket(5647);

            // InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection
            Socket s;

            // obtaining input and out streams

            while (true) {
                s = ss.accept();

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                String str = dis.readUTF();
                System.out.println(str);

                // byte[] b1 = new byte[1024];

                // DatagramPacket dp = new DatagramPacket(b1, b1.length);
                // ds.receive(dp);

                // String str = new String(dp.getData(), 0, dp.getLength());

                StringTokenizer st = new StringTokenizer(str, "#");

                String request = st.nextToken();

                if (request.equals("1")) {

                    String user = st.nextToken();
                    String u_pw = st.nextToken();
                    Statement stat = con.createStatement();
                    ResultSet rs = stat.executeQuery("select name,pw from login;");
                    int t = 0;
                    String out = "";
                    while (rs.next()) {
                        if (user.equals(rs.getString(1)) && u_pw.equals(rs.getString(2))) {

                            t = 1;
                            System.out.println("Logged in");

                            PreparedStatement preparedStmt = con
                                    .prepareStatement("update login set status=1 where name='" + user + "';");
                            preparedStmt.execute();

                            out = "Sucess";
                            break;

                        }
                        // System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
                    }
                    if (t == 0) {
                        out = "Fail";
                    }
                    // byte[] b2 = out.getBytes();
                    // InetAddress ia = InetAddress.getLocalHost();
                    // DatagramPacket dp1 = new DatagramPacket(b2, b2.length, ia, dp.getPort());
                    // ds.send(dp1);

                    dos.writeUTF(out);

                } else if (request.equals("2")) {

                    String user = st.nextToken();
                    String u_pw = st.nextToken();

                    PreparedStatement preparedStmt = con.prepareStatement(
                            "insert into login (name,status,pw) values('" + user + "',1," + u_pw + ");");
                    preparedStmt.execute();

                    PreparedStatement preparedStmt1 = con.prepareStatement(
                            "create table " + user + " (sender varchar(25), receiver varchar(25) , msg varchar(500));");
                    preparedStmt1.execute();

                } else if (request.equals("3")) {
                    String user = st.nextToken();

                    PreparedStatement preparedStmt = con
                            .prepareStatement("update login set status=0 where name='" + user + "';");
                    preparedStmt.execute();

                } else if (request.equals("4")) {
                    String user = st.nextToken();
                    Statement stat = con.createStatement();
                    ResultSet rs = stat.executeQuery("select name,status from login;");
                    String out = "";
                    while (rs.next()) {
                        // System.out.println(rs.getString(1) + " " + user + " " + rs.getString(1) !=
                        // user);
                        if (!rs.getString(1).equals(user)) {
                            out += rs.getString(1) + " - ";
                            if (Integer.parseInt(rs.getString(2)) == 0)
                                out += "Offline\n";
                            else if (Integer.parseInt(rs.getString(2)) == 1)
                                out += "Online\n";

                        }
                    }
                    dos.writeUTF(out);

                } else if (request.equals("5")) {
                    String sender = st.nextToken();
                    Statement stat = con.createStatement();
                    System.out.println("2 sent");
                    ResultSet rs = stat.executeQuery("select status from login where name='" + sender + "';");
                    System.out.println("3 sent");
                    int status = 0;

                    while (rs.next()) {
                        status = Integer.parseInt(rs.getString(1));

                    }
                    String out = "";

                    if (status == 0) {
                        out = "Offline";
                    } else {
                        out = "Online";
                    }
                    dos.writeUTF(out);
                    System.out.println("4 sent");

                } else if (request.equals("6")) {
                    String user = st.nextToken();
                    String sender = st.nextToken();
                    String chat = "";
                    System.out.println("7 sent");
                    Statement stat = con.createStatement();
                    ResultSet rs = stat.executeQuery("select sender,receiver,msg from " + user + " where sender='"
                            + sender + "' or receiver='" + sender + "';");
                    while (rs.next()) {
                        chat += rs.getString(1) + " : " + rs.getString(3) + "\n";
                    }
                    System.out.println("8 sent");
                    dos.writeUTF(chat);
                } else if (request.equals("7")) {
                    System.out.println("in1");

                    String user = st.nextToken();
                    System.out.println("in2");
                    String sender = st.nextToken();
                    System.out.println("in3");
                    String msg = st.nextToken();
                    System.out.println("13 sent");
                    PreparedStatement preparedStmt = con.prepareStatement("insert into " + user
                            + " (sender,receiver,msg) values('" + user + "','" + sender + "','" + msg + "');");
                    preparedStmt.execute();
                    System.out.println("14 sent");

                    PreparedStatement preparedStmt1 = con.prepareStatement("insert into " + sender
                            + " (sender,receiver,msg) values('" + user + "','" + sender + "','" + msg + "');");
                    preparedStmt1.execute();
                    System.out.println("15 sent");

                    dos.writeUTF(user + "#" + sender + "#" + msg);

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}