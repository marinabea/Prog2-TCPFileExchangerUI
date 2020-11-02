package sensorData;

import java.io.*;

public class SensorDataExchanger implements SensorDataSender, SensorDataReceiver {

    @Override
    public void sendSensorData(SensorData data, OutputStream os) throws IOException {
        //DataOutputStream nutzen:
        DataOutputStream daos = new DataOutputStream(os);

        // Daten über DataOutputStream senden:
        daos.writeLong(data.getTimeStamp());
        daos.writeFloat(data.getValue());
        daos.writeUTF(data.getSensorName());

        os.close();
    }

    @Override
    public SensorData receiveSensorData(InputStream is) throws IOException {

        DataInputStream dais = new DataInputStream(is);

        // Daten durch DataInputStream empfangen:
        long timeStamp = dais.readLong();
        float value = dais.readFloat();
        String sensorName = dais.readUTF();

        dais.close();


        //from private class sensordataimpl
        return new SensorDataImpl(timeStamp, value, sensorName);
    }
}
