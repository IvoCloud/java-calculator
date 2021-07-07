package app;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;

public class Client {
    public static void main(String[] args) throws  IOException {
        Socket s = new Socket("localhost",4999);

//        One way, client to server
         PrintWriter pr = new PrintWriter(s.getOutputStream());
         pr.println("Hello from client");
         pr.flush();

//        *******************************************

//        Two way
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("Server says: " + str);
    }
}
