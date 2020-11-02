package sensorData;

public interface SensorData {

    /**
     *
     * @return time stamp of measurement
     */
    long getTimeStamp();

    /**
     *
     * @return value measured by sensor
     */
    float getValue();

    /**
     *
     * @return sensor name
     */
    String getSensorName();
}
