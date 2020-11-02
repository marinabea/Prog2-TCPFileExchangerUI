package TCP;

import java.io.*;
import java.net.Socket;

public class TCPClient {

    public static String hostname;
    public static int portnr;
    private final static String HOST = "localhost";
    private final static int PORT = 3111;

    public static void main(String[] args) throws IOException, InterruptedException {
       // dieses Programm soll als Parameter den host und den port entgegennehmen, die verbunden werden sollen
       /* if (args.length < 2 ) {
            System.out.println("Missing parameters. Required: hostname portnumber");
        } */
        String hostnamepm = args[0];
        String portString = args[1];
        int portno = Integer.parseInt(portString);
        String fileName = null;

        // wenn ein Argument mehr übergeben wird, handelt es sich dabei um den Dateinamen
        if (args.length > 2 ) {
            fileName = args[2];
        }

        TCPClient Client = new TCPClient(hostnamepm, portno);

        if (fileName != null) {
            Client.copyFile(fileName);
        } else {
            // Client.connectToServer();
            long timeStamp = System.currentTimeMillis();
            float value = (float) 42.0;
            String sensorName = "Sensor A";
            Client.sendSensorData(timeStamp, value, sensorName);
         }
    }

    private void sendSensorData(long timeStamp, float value, String sensorName) throws IOException {
        Socket sensorSocket = new Socket(this.hostname, this.portnr);
        OutputStream os = sensorSocket.getOutputStream();

        //DataOutputStream nutzen:
        DataOutputStream daos = new DataOutputStream(os);
        // Daten über DataOutputStream senden:
        daos.writeLong(timeStamp);
        daos.writeFloat(value);
        daos.writeUTF(sensorName);

        daos.close();
    }

    private void copyFile(String fileName) throws IOException {
        Socket copysocket = new Socket(this.hostname, this.portnr);
        FileInputStream fis = new FileInputStream(fileName);
        OutputStream os = copysocket.getOutputStream();

        int read = 0;
        do {
            read = fis.read();
            if (read != -1) {
                os.write(read);
            }
        } while (read != 1);
        os.close();
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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();    // Buffer-Array, um zu sendende Nachricht zu übertragen
        int i = 0;  // Zähler, um alle zu sendenden Daten in den Buffer zu schreiben

        is.read(); //wenn -1 zurückgegeben wird, kann Server auch nichts mehr lesen

        // deshalb: über eine Schleife
        //Eingangswert:
        int read = 0;
        do {
            read = is.read();   //read hat den selben Wert wie is.read(), d.h. so viele Bytes wie es noch zu lesen gibt
            if(read != -1) {    //wenn read nicht -1 ist, also noch gelesen werden kann ...
                baos.write(read);  // ... gelesene Bytes in den Buffer schreiben
            }

        } while (read != -1);

        //jetzt haben wir alle Daten in den Buffer gelesen
        // wie viele? steht in i

        /*-----------------------------------------------------------*/
        //als String nur die tatsächlich empfangenen Bytes ausgeben, nicht die volle Länge des angelegen Buffer


        String received = new String(baos.toByteArray());
        System.out.println("Server hat bekommen: " + received);
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