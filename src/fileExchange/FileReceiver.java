package fileExchange;

import java.io.IOException;
import java.io.InputStream;

public interface FileReceiver {

    /**
     * Receive content from input stream, write to local file
     * @param filename local file name
     * @param is content provider
     */
    void receiveFile(String filename, InputStream is) throws IOException;
}
