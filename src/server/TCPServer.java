
package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private final int port;
    public static final int PORTNR = 3111;

    public static void main(String[] args) throws IOException, InterruptedException {

        TCPServer tcpServer = new TCPServer(PORTNR);

        tcpServer.connect();
    }

    private void connect() throws IOException, InterruptedException {
        ServerSocket srvsocket = new ServerSocket(this.port);
        System.out.println("Server erstellt - Verbindung jetzt möglich");
        Socket socket = srvsocket.accept();    //Server akzeptiert Clients, die auf den Port zugreifen wollen. Rückgabe eines Sockets = Verbindung zwischen Server und Client
        System.out.println("Client akzeptiert");
        socket.getInputStream().read();     //inputstream aus dem socket lesen
        System.out.println("Input gelesen");
        OutputStream os = socket.getOutputStream(); //aus dem Socket gelesener Outputstream in Objekt os zwischenspeichern
        os.write("Hallo lieber Client".getBytes()); //in den OS schreibt der Server
        System.out.println("Nachricht geschrieben");
        Thread.sleep(5000);
        os.close();
        System.out.println("Serverseitige Übertragung beendet");
    }

    TCPServer(int port){
        this.port = port;
    }
}
    /*
        try {
            //neuen Serversocket erstellen
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server gestartet");

            //client vom server akzeptieren lassen
            Socket client = server.accept();

            //Streams
            //ZUERST vom Client bekommen
            InputStream is = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            //Ausgabe der vom Client empfangenen Nachrichten:
            String empfangen = null;
            while ((empfangen = reader.readLine()) != null) {
                System.out.println("Empfangen von Client: " + empfangen);
            }

            //DANN an den Client schicken:
            OutputStream os = client.getOutputStream();
            PrintWriter writer = new PrintWriter(os);
            os.write("hallo Client".getBytes());


            //Pause 5sek
            Thread.sleep(5000);

            //writer und reader schließen
            writer.close();
            reader.close();
            System.out.println("Übertragung beendet");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}


     */