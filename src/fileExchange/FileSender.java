package fileExchange;

import java.io.IOException;
import java.io.OutputStream;

public interface FileSender {

    /**
     * Send file from local program to another process via outputstream
     * @param filename local file name
     * @param os connection / stream to remote entity
     */
    void sendFile(String filename, OutputStream os) throws IOException;

}
