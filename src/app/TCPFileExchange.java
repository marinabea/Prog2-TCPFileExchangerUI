package app;

import java.io.IOException;

public interface TCPFileExchange {

    /**#
     * send file from local to remote host via tcp
     * @param filename
     * @param hostname
     * @param port
     */
    void sendFile2Host(String filename, String hostname, int port) throws IOException;

    /**
     * receive content from TCP client and write to local file
     * @param filename local file name
     * @param port to accept connection
     */
    void receiveFile(String filename, int port) throws IOException;
}
