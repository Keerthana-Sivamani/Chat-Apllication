import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
//import java.io.ObjectInputFilter.Status;
//import java.sql.*;
import java.net.*;
import java.util.StringTokenizer;

public class login extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1;
    JTextField t2;
    JButton b1;
    JButton b2;
    // static DatagramSocket ds;
    static Socket s;

    login() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        JLabel l3 = new JLabel("Login page");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(70, 30, 100, 20);
        p1.add(l3);

        ImageIcon i1 = new ImageIcon("C:/Users/SHIVAMANI/Desktop/zoho/GUI/icons/exit.png");
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(400, 15, 40, 40);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        JLabel l4 = new JLabel("User name :");
        l4.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        l4.setForeground(Color.black);
        l4.setBounds(80, 170, 100, 30);
        add(l4);

        t1 = new JTextField();
        t1.setBounds(180, 170, 200, 30);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        JLabel l5 = new JLabel("Password  :");
        l5.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        l5.setForeground(Color.black);
        l5.setBounds(80, 220, 100, 30);
        add(l5);

        t2 = new JTextField();
        t2.setBounds(180, 220, 200, 30);
        t2.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t2);

        b1 = new JButton("Login");
        b1.setBounds(155, 270, 150, 30);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        b1.addActionListener((ActionListener) this);
        add(b1);

        b2 = new JButton("Create new account");
        b2.setBounds(80, 380, 300, 30);
        b2.setFont(new Font("SAN_SERIF", Font.PLAIN, 12)); //
        b2.addActionListener(this);
        add(b2);

        setLayout(null);
        setSize(450, 700);
        setLocation(0, 10);
        // setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String user = t1.getText();
                String u_pw = t2.getText();

                InetAddress ia = InetAddress.getByName("localhost");

                // establish the connection
                s = new Socket(ia, 5647);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // ds = new DatagramSocket(5647);
                // ds = new DatagramSocket(5647);

                String send = "1#" + user + "#" + u_pw;
                /*
                 * byte[] b = send.getBytes(); InetAddress ia = InetAddress.getLocalHost();
                 * DatagramPacket dp = new DatagramPacket(b, b.length, ia, 5647); ds.send(dp);
                 */
                dos.writeUTF(send);

                // byte[] b2 = send.getBytes();
                // InetAddress ia = InetAddress.getLocalHost();
                // DatagramPacket dp = new DatagramPacket(b2, b2.length, ia, 5647);
                // ds.send(dp);

                String msg = dis.readUTF();

                // String msg = new String(dp1.getData());
                // byte[] b1 = new byte[1024];
                // DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
                // ds.receive(dp1);
                // String msg = new String(dp1.getData());
                s.close();

                if (msg.equals("Sucess")) {
                    new activeuser(user).setVisible(true);
                    this.dispose();

                }

                else if (msg.equals("Fail")) {
                    t1.setText("");
                    t2.setText("");
                    JOptionPane.showMessageDialog(null, "Incorrect Password");

                }
            } else if (ae.getSource() == b2) {
                // s.close();
                new addaccount().setVisible(true);
                this.dispose();

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) {
        new login().setVisible(true);
    }

}

class addaccount extends JFrame implements ActionListener {

    JPanel p1;
    JTextField t1;
    JTextField t2;
    JButton b1;
    JButton b2;
    // static DatagramSocket ds;
    Socket s;

    addaccount() {
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        JLabel l3 = new JLabel("Sign up page");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(70, 20, 200, 30);
        p1.add(l3);

        ImageIcon i1 = new ImageIcon("C:/Users/SHIVAMANI/Desktop/zoho/GUI/icons/exit.png");
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(400, 15, 40, 40);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        JLabel l4 = new JLabel("User name :");
        l4.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        l4.setForeground(Color.black);
        l4.setBounds(80, 170, 100, 30);
        add(l4);

        t1 = new JTextField();
        t1.setBounds(180, 170, 200, 30);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        JLabel l5 = new JLabel("Password  :");
        l5.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
        l5.setForeground(Color.black);
        l5.setBounds(80, 220, 100, 30);
        add(l5);

        t2 = new JTextField();
        t2.setBounds(180, 220, 200, 30);
        t2.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t2);

        b1 = new JButton("Register");
        b1.setBounds(155, 270, 150, 30);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Go to login page");
        b2.setBounds(80, 380, 300, 30);
        b2.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        b2.addActionListener(this);
        add(b2);

        setLayout(null);
        setSize(450, 700);
        setLocation(0, 10);
        // setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String user = t1.getText();
                String u_pw = t2.getText();

                InetAddress ip = InetAddress.getByName("localhost");
                s = new Socket(ip, 5647);

                // obtaining input and out streams
                // DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // ds = new DatagramSocket(5647);

                String send = "2#" + user + "#" + u_pw;
                dos.writeUTF(send);
                s.close();

                JOptionPane.showMessageDialog(null, "Account Created");

            } else if (ae.getSource() == b2) {
                new login().setVisible(true);
                this.dispose();

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

class activeuser extends JFrame implements ActionListener {
    JPanel p1;
    JTextField t1;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    static JTextArea a1;
    static String user;

    // static DatagramSocket ds;
    // static int port;
    Socket s;

    activeuser(String u_ser) {

        user = u_ser;

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        ImageIcon i1 = new ImageIcon("C:/Users/SHIVAMANI/Desktop/zoho/GUI/icons/exit.png");
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(400, 15, 40, 40);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                try {
                    InetAddress ip = InetAddress.getByName("localhost");
                    s = new Socket(ip, 5647);

                    // obtaining input and out streams
                    // DataInputStream dis = new DataInputStream(s.getInputStream());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                    // ds = new DatagramSocket(5647);

                    String send = "3#" + user;
                    dos.writeUTF(send);
                    s.close();

                } catch (Exception e) {
                    //
                }

                System.exit(0);
            }
        });

        JLabel l3 = new JLabel("Contact List");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(70, 28, 200, 20);
        p1.add(l3);

        t1 = new JTextField();
        t1.setBounds(10, 80, 315, 30);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        b1 = new JButton("Message");
        b1.setBounds(335, 80, 105, 30);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        add(b1);

        a1 = new JTextArea();
        //
        // scroll.setSize(430, 1000);
        a1.setBounds(10, 120, 430, 570);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        // scroll = new JScrollPane(a1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        // JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(a1);

        try {
            InetAddress ip = InetAddress.getByName("localhost");
            s = new Socket(ip, 5647);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // ds = new DatagramSocket(5647);

            String send = "4#" + user;
            dos.writeUTF(send);
            String str = dis.readUTF();
            a1.setText(str);
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        getContentPane().setBackground(Color.lightGray);

        setLayout(null);
        setSize(450, 700);
        setLocation(460, 10);
        // setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String sender = t1.getText();
            new Client(sender, user).setVisible(true);
            this.dispose();

        } catch (Exception e) {
            System.out.println("x");
        }

    }

}

class Client extends JFrame implements ActionListener {
    JPanel p1;
    JTextField t1;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton l2;
    static JTextArea a1;
    static String user;
    static String sender;

    // static DatagramSocket ds;
    // static int port;
    static String status;
    static Socket s;
    static receive r;
    static settext st;

    Client(String s_ender, String u_ser) {

        user = u_ser;
        sender = s_ender;

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        ImageIcon i4 = new ImageIcon("C:/Users/SHIVAMANI/Desktop/zoho/GUI/icons/mickey.png");
        Image i5 = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        l2 = new JButton(i6);
        l2.setBounds(15, 15, 40, 40);
        l2.addActionListener(this);
        p1.add(l2);

        JLabel l3 = new JLabel(sender);
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(70, 15, 100, 20);
        p1.add(l3);
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            s = new Socket(ip, 5647);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // ds = new DatagramSocket(5647);

            String send = "5#" + sender;
            dos.writeUTF(send);
            System.out.println("1 sent");
            String str = dis.readUTF();
            System.out.println("5 sent");
            status = str;
            s.close();

        } catch (Exception e) {
            //
        }

        JLabel l4 = new JLabel(status);
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        l4.setForeground(Color.WHITE);
        l4.setBounds(70, 40, 100, 15);
        p1.add(l4);

        a1 = new JTextArea();
        a1.setBounds(10, 80, 430, 540);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        a1.setBackground(Color.LIGHT_GRAY);
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        add(a1);

        try {
            String chat = "";
            InetAddress ip = InetAddress.getByName("localhost");
            s = new Socket(ip, 5647);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // ds = new DatagramSocket(5647);

            String send = "6#" + user + "#" + sender;
            dos.writeUTF(send);
            System.out.println("6 sent");
            chat = dis.readUTF();
            System.out.println("9 sent");
            s.close();

            a1.setText(chat);
        } catch (Exception e) {
            System.out.println(e);
        }

        t1 = new JTextField();
        t1.setBounds(10, 630, 360, 30);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        b1 = new JButton("Send");
        b1.setBounds(375, 630, 70, 30);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        b1.addActionListener(this);
        add(b1);

        r = new receive(user, sender, a1);
        st = new settext(s, user, sender, a1);

        Thread sendMessage = new Thread(r);
        Thread f = new Thread(st);

        // Spawn a thread for reading messages

        getContentPane().setBackground(Color.lightGray);

        setLayout(null);
        setSize(450, 700);
        setLocation(920, 10);
        // setUndecorated(true);
        setVisible(true);
        // sendMessage.start();
        f.start();

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {
                String msg = t1.getText();
                String out = user + " : " + msg;

                a1.setText(a1.getText() + "\n" + out);

                InetAddress ip = InetAddress.getByName("localhost");
                s = new Socket(ip, 5647);

                // obtaining input and out streams
                // DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // ds = new DatagramSocket(5647);

                String send = "7#" + user + "#" + sender + "#" + msg;
                System.out.println("12 sent");
                dos.writeUTF(send);
                System.out.println("out");

                s.close();

                t1.setText("");

            }

            if (ae.getSource() == l2) {
                // (sendMessage.s).close();
                // sendMessage.stop();
                System.out.println("keer");
                st.stop();
                this.dispose();
                try {
                    new activeuser(user).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }

                System.out.println("varun");

            }

        } catch (Exception e) {
            System.out.println("x");
        }

    }

}

class settext implements Runnable {
    String user;
    String sender;
    Socket s;
    JTextArea a1;
    boolean exit;

    settext(Socket s, String user, String sender, JTextArea a1) {
        this.sender = sender;
        this.user = user;
        this.s = s;
        this.a1 = a1;
        this.exit = true;
    }

    public void run() {
        while (exit) {
            try {

                String chat = "";
                InetAddress ip = InetAddress.getByName("localhost");
                s = new Socket(ip, 5647);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // ds = new DatagramSocket(5647);

                String send = "6#" + user + "#" + sender;
                dos.writeUTF(send);
                System.out.println("6 sent");
                chat = dis.readUTF();
                System.out.println("9 sent");

                a1.setText(chat);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        try {
            s.close();
        } catch (Exception e) {
            //
        }

    }

    public void stop() {
        this.exit = false;
    }
}

class receive implements Runnable {
    Socket s;
    String user;
    String sender;
    JTextArea a1;
    boolean exit;

    receive(String user, String sender, JTextArea a1) {
        this.user = user;
        this.sender = sender;
        this.a1 = a1;
        this.exit = true;

    }

    public void run() {
        try {
            InetAddress ip = InetAddress.getByName("localhost");

            s = new Socket(ip, 5647);

            while (exit) {

                // DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                // ds = new DatagramSocket(5647);
                DataInputStream dis = new DataInputStream(s.getInputStream());

                System.out.println("10 sent");

                String str = dis.readUTF();
                System.out.println("11 sent");
                // s.close();

                StringTokenizer st = new StringTokenizer(str, "#");

                String u_ser = st.nextToken();
                String s_ender = st.nextToken();

                if ((user.equals(u_ser) && sender.equals(s_ender)) || (user.equals(s_ender) && sender.equals(u_ser)))
                    a1.setText(a1.getText() + "\n" + str);

            }
        } catch (Exception e) {
            //
        }

    }

    public void stop() {
        try {
            this.exit = false;
            this.s.close();
        } catch (Exception e) {
            //
        }

    }

}

/*
 * class ReadThread implements Runnable { private DatagramSocket ds; String
 * user; String sender; JTextField a1;
 * 
 * // private InetAddress group; // private int port; // private static final
 * int MAX_LEN = 1000; ReadThread(DatagramSocket ds, String user, String sender,
 * JTextField a1) { this.ds = ds; this.user = user; this.sender = sender;
 * this.a1 = a1; }
 * 
 * public void run() { try {
 * 
 * ds = new DatagramSocket(5647); Socket s;
 * 
 * while (true) { InetAddress ip = InetAddress.getByName("localhost"); s = new
 * Socket(ip, 5647);
 * 
 * // obtaining input and out streams DataInputStream dis = new
 * DataInputStream(s.getInputStream()); // DataOutputStream dos = new
 * DataOutputStream(s.getOutputStream());
 * 
 * // ds = new DatagramSocket(5647); System.out.println("10 sent");
 * 
 * String str = dis.readUTF(); System.out.println("11 sent"); s.close();
 * 
 * StringTokenizer st = new StringTokenizer(str, "#");
 * 
 * String u_ser = st.nextToken(); String s_ender = st.nextToken();
 * 
 * if ((user.equals(u_ser) && sender.equals(s_ender)) || (user.equals(s_ender)
 * && sender.equals(u_ser))) a1.setText(a1.getText() + "\n" + str);
 * 
 * }
 * 
 * // skt.close(); // s.close();
 * 
 * } catch (Exception e) { System.out.println("e"); } } }
 */