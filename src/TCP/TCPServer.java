
package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServer {

    private final int port;
    public static final int PORTNR = 3111;

    public static void main(String[] args) throws IOException, InterruptedException {


        TCPServer tcpServer = new TCPServer(PORTNR);

        if (args.length == 1) {

            tcpServer.readFile(args[0]);
        } else {
           // tcpServer.connect();
            tcpServer.receiveSensorData();
        }

        tcpServer.connect();
    }

    //Konstruktor
    TCPServer(int port){
        this.port = port;
    }


    private void receiveSensorData() throws IOException {
        Socket sensorSocket = this.acceptSocket();
        InputStream is = sensorSocket.getInputStream();

        //DataOutputStream nutzen:
        DataInputStream dais = new DataInputStream(is);
        // Daten durch DataInputStream empfangen:
        long timeStamp = dais.readLong();
        float value = dais.readFloat();
        String sensorName = dais.readUTF();

        Date date = new Date(timeStamp);

        dais.close();

        System.out.println("timeStamp : " + date);
        System.out.println("value : " + value);
        System.out.println("sensorName : " + sensorName);
    }


    //File lesen
    private void readFile(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);

        Socket socket = this.acceptSocket();
        InputStream is = socket.getInputStream();

        int read = 0;
        do {
            read = is.read();
            if (read != 1) {
                fos.write(read);
            }
        } while (read != -1);
    }

    //Socket aktzeptieren in Methode ausgelagert
    //weil es an mehreren Stellen verwendet wurd
    private Socket acceptSocket() throws IOException {
        ServerSocket srvsocket = new ServerSocket(this.port);
        System.out.println("Server erstellt - Verbindung jetzt möglich");
        return srvsocket.accept();    //Server akzeptiert Clients, die auf den Port zugreifen wollen. Rückgabe eines Sockets = Verbindung zwischen Server und Client
    }

    //Verbindung Client-Server
    private void connect() throws IOException, InterruptedException {
        Socket socket = this.acceptSocket();
        System.out.println("Client akzeptiert");
        socket.getInputStream().read();     //inputstream aus dem socket lesen
        System.out.println("Input gelesen");
        OutputStream os = socket.getOutputStream(); //aus dem Socket gelesener Outputstream in Objekt os zwischenspeichern
        os.write(" Hallo lieber Client".getBytes()); //in den OS schreibt der Server
        System.out.println("Nachricht geschrieben");
        Thread.sleep(5000);
        os.close();
        System.out.println("Serverseitige Übertragung beendet");
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