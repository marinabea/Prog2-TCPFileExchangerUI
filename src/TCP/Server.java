package TCP;

import java.io.IOException;

public interface Server {

    Connection acceptConnection(int port) throws IOException;
}
