package util;

import app.TCPFileExchange;
import app.TCPFileExchangeImpl;

import java.io.IOException;

/**
 * Nutzeroberfläche FileTransfer
 * expected params:
 * 1) send: filename, hostname (recipient), port
 * 2) receive: filename, port
 */

public class TCPFileExchangerUI {

    public static void main(String[] args) throws IOException {

        if(args.length < 2) {
            System.err.println("at least 2 arguments required");
            return;
        }

        String filename = args[0];
        String hostname = null;
        int port = -1;


        String portString = null;
        if(args.length == 3) { // > case: send
            hostname = args[1];
            portString = args[2];
        } else if(args.length == 2) { // > case: send
            portString = args[1];
        }

        port = Integer.parseInt(portString);

        TCPFileExchange tcpFileExchanger = new TCPFileExchangeImpl();

        if(hostname == null) {
            //receive
            tcpFileExchanger.receiveFile(filename, port);
        } else {
            //send
            tcpFileExchanger.sendFile2Host(filename, hostname, port);
        }

    }


}
