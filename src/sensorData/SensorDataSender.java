package sensorData;

import java.io.IOException;
import java.io.OutputStream;

public interface SensorDataSender {

    /**
     * send sensor data set
     * @param data (timestamp, value, sensor name)
     * @param os stream to recipient
     */
    void sendSensorData(SensorData data, OutputStream os) throws IOException;

}
