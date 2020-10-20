package client;


import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    public static String hostname;
    public static int portnr;

    TCPClient(String hostname, int portnr) throws IOException {
        TCPClient.hostname = hostname;
        TCPClient.portnr = portnr;
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        TCPClient Client = new TCPClient("localhost", 3100);
        Client.connectToServer();

    }

    public void connectToServer() throws IOException, InterruptedException {
        Socket socket = new Socket(TCPClient.hostname, TCPClient.portnr);
        System.out.println("Client mit Server verbunden");
        OutputStream output = socket.getOutputStream();
        output.write("Hallo Server".getBytes());
        System.out.println("Client hat Nachricht verschickt");
        socket.getInputStream().read();
        Thread.sleep(500);
        socket.close();     //Socketverbindung geschlossen
        System.out.println("Ende der Übertragung");
    }
}




       /* int len = args.length;          //len = Anzahl der Parameter

        String hostname = args[0];      //erstes Argument ist hostname
        String portString = args[1];    //2. argument Portnummer als String
        int portnr = Integer.parseInt(portString);  //PortnummerString in integer geparsed

        System.out.println("Host: " + hostname);
        System.out.println("Port: " + portnr);
   */