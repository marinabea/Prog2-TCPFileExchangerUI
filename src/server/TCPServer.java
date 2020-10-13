package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) {

        int port = 3100;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server gestartet");

            Socket client = server.accept();

            //Streams
            //an den Client schicken:
            OutputStream os = client.getOutputStream();
            PrintWriter writer = new PrintWriter(os);

            //vom Client bekommen
            InputStream is = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            //Ausgabe der vom Client empfangenen Nachrichten:
            String empfangen = null;
            while ((empfangen = reader.readLine()) != null) {
                System.out.println("Empfangen von Client: " + empfangen);
            }


            Thread.sleep(5000);

            writer.close();
            reader.close();


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
