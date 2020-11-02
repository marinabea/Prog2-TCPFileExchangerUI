package app;

import sensorData.SensorData;

import java.io.IOException;

public interface TCPSensorDataExchanger {

    /**
     * send sensor data to host
     * @param data
     * @param hostname
     * @param port
     */
    void sendSensorData(SensorData data, String hostname, int port) throws IOException;

    /**
     * listen to port and receive sensor data from that port
     * @param port
     */
    void receiveSensorData(int port) throws IOException;
}
