package TCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Connection {

    /**
     *
     * @return outputstraem of created connection
     */
    OutputStream getOutputStream() throws IOException;

    /**
     *
     * @return inputstraem of created connection
     */
    InputStream getInputStream() throws IOException;

}
