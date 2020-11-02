package app;

import TCP.Client;
import TCP.Connection;
import TCP.Server;
import TCP.TCPConnector;
import sensorData.SensorData;
import sensorData.SensorDataExchanger;
import sensorData.SensorDataReceiver;
import sensorData.SensorDataSender;

import java.io.IOException;

public class TCPSensorDataExchangerImpl implements TCPSensorDataExchanger {
    @Override
    public void sendSensorData(SensorData data, String hostname, int port) throws IOException {
        Client client = new TCPConnector();
        Connection connection = client.connect(hostname, port);

        SensorDataSender sensorDataSender = new SensorDataExchanger();
        sensorDataSender.sendSensorData(data, connection.getOutputStream());

    }

    @Override
    public void receiveSensorData(int port) throws IOException {
        Server server = new TCPConnector();
        Connection connection = server.acceptConnection(port);

        SensorDataReceiver sensorDataReceiver = new SensorDataExchanger();
        sensorDataReceiver.receiveSensorData(connection.getInputStream());

    }
}
