package app;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException{
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "        <!-- head definitions go here -->\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <!-- the content goes here -->\n" +
                "    </body>\n" +
                "</html>";

        ServerSocket ss = new ServerSocket(4999);
        Socket s = ss.accept();

            System.out.println("Client connected");

            //One way, client to server
            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            String str = bf.readLine();
            System.out.println("Client says: " + str);

            //*******************************************

            //Two way
            PrintWriter pr = new PrintWriter(s.getOutputStream());
            pr.println(html);
            pr.flush();
    }
}