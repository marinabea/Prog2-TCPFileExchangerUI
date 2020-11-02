package sensorData;

import java.io.IOException;
import java.io.InputStream;

public interface SensorDataReceiver {

    /**
     * receive data from stream and create new sensor data object
     * @param is inputstream for receiving data
     * @return sensor data
     */
    SensorData receiveSensorData(InputStream is) throws IOException;
}
