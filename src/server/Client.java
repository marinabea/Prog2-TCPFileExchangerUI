package server;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    String name = "localhost";
    int port = 3100;
    Socket socket = new Socket(name, port);

    /** nee das stimmt so nicht
    OutputStream os = server.getOutputStream();
    PrintWriter writer = new PrintWriter(os);
    os.write("hallo Server".getBytes());
    **/

    public Client() throws IOException {
        System.out.println("Client hat nicht geklappt");
    }
}
