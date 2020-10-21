package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

    public static String hostname;
    public static int portnr;
    private final static String HOST = "localhost";
    private final static int PORT = 3111;

    public static void main(String[] args) throws IOException, InterruptedException {
        TCPClient Client = new TCPClient(HOST, PORT);
        Client.connectToServer();

    }

    TCPClient(String hostname, int portnr) throws IOException {
        TCPClient.hostname = hostname;
        TCPClient.portnr = portnr;
    }

    public void connectToServer() throws IOException, InterruptedException {
        Socket socket = new Socket(this.hostname, this.portnr);
        socket.getOutputStream().write("Hallo lieber Server".getBytes());
       /* OutputStream output = socket.getOutputStream();
        output.write("Hallo Server".getBytes()); */
        System.out.println("Client mit Server verbunden");

        System.out.println("Client hat Nachricht verschickt");
        InputStream is = socket.getInputStream();

        byte[] buffer = new byte[10000];    // Buffer-Array, um zu sendende Nachricht zu übertragen
        int i = 0;  // Zähler, um alle zu sendenden Daten in den Buffer zu schreiben

        is.read(); //wenn -1 zurückgegeben wird, kann Server auch nichts mehr lesen

        // deshalb: über eine Schleife
        //Eingangswert:
        int read = 0;
        do {
            read = is.read();   //read hat den selben Wert wie is.read(), d.h. so viele Bytes wie es noch zu lesen gibt
            if(read != -1) {    //wenn read nicht -1 ist, also noch gelesen werden kann ...
                byte readByte = (byte) read;    // ( durch parsen unnötige symbolische Byte abschneiden )
                buffer[i++] = readByte;   // ... gelesene Bytes in den Buffer schreiben
            }

        } while (read != -1);

        //jetzt haben wir alle Daten in den Buffer gelesen
        // wie viele? steht in i

        /*-----------------------------------------------------------*/
        //als String nur die tatsächlich empfangenen Bytes ausgeben, nicht die volle Länge des angelegen Buffers
        byte[] receivedBytes = new byte[i];
        for (int j = 0; j < 1; j++) {
            receivedBytes[j] = buffer[j];
        }
        String received = new String(buffer);
        System.out.println("received: " + received);
        /*-----------------------------------------------------------*/

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