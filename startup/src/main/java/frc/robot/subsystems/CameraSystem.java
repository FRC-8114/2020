package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.*;
import edu.wpi.cscore.*;

import java.util.Arrays;

import org.opencv.core.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class CameraSystem extends SubsystemBase{
    private NetworkTableInstance inst;
    private NetworkTableEntry points;
    private NetworkTable table;
    private double[] locations;
    private GripPipeline gripPipeline;
    private I2C arduino;

    //private double[] minPos = new double[2], maxPos = new double[2];

    public CameraSystem() {
        gripPipeline = new GripPipeline();
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("");

        points = table.getEntry("points");
        locations = new double[12];

        //minPos = table.getEntry("minPos").getDoubleArray(new double[2]);
        //maxPos = table.getEntry("maxPos").getDoubleArray(new double[2]);

        new Thread(() -> {
            UsbCamera mainCamera = CameraServer.getInstance().startAutomaticCapture();
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Source", 320, 240);

            Mat source = new Mat();

            while(!Thread.interrupted()) {
                if (cvSink.grabFrame(source)==0) {
                    continue;
                }
                gripPipeline.process(source);
                outputStream.putFrame(gripPipeline.cvRectangleOutput());
            }
        }).start();*/
        arduino = new I2C(Port.kOnboard, 0);
    }

    public double[] getDistance() {
        /**
         * order of recv
         * width - int (4 bytes)
         * height - int (4 bytes)
         * x - int (4 bytes)
         * y - int (4 bytes)
         * distance - int (8 bytes)
         * angle - int (8 bytes) */
        byte[] recv = new byte[4 + 4 + 4 + 4 + 8 + 8];
        arduino.readOnly(recv, 32);
        double[] arduinoParams = new double[6];
        arduinoParams[0] = (double)reconvertInt(Arrays.copyOfRange(recv, 0, 3));
        arduinoParams[1] = (double)reconvertInt(Arrays.copyOfRange(recv, 4, 7));
        arduinoParams[2] = (double)reconvertInt(Arrays.copyOfRange(recv, 8, 11));
        arduinoParams[3] = (double)reconvertInt(Arrays.copyOfRange(recv, 12, 15));
        arduinoParams[4] = reconvertDouble(Arrays.copyOfRange(recv, 16, 23));
        arduinoParams[5] = reconvertDouble(Arrays.copyOfRange(recv, 24, 32));
        System.out.println(Arrays.toString(arduinoParams));
        return arduinoParams;
    }

    public void assignPoints() {
        double[] points = this.points.getDoubleArray(locations);
        for(int i=0;i<locations.length;i++) {
            locations[i] = points[i];
        }

        /*
        * locations[0]: top left x  
        * locations[1]: top left y
        * locations[2]: top right x
        * locations[3]: top right y
        * locations[4]: middle top left x
        * locations[5]: middle top left y
        * locations[6]: middle top right x
        * locations[7]: middle top right y
        * locations[8]: middle bottom left x
        * locations[9]: middle bottom left y
        * locations[10]: middle bottom right x
        * locations[11]: middle bottom right y
        */
    }

    public void periodic() {
        /*
        minPos = table.getEntry("minPos").getDoubleArray(new double[2]);
        maxPos = table.getEntry("maxPos").getDoubleArray(new double[2]);

        for(double d : minPos)
            System.out.print(d +" ");
        System.out.println("\n");
        for(double d : maxPos)
            System.out.println(d +" ");
        */
    }

    public void autoAim() {
        assignPoints();

        findDistance(0.0508, 0, (locations[7] - locations[11]), 0);

        // In meters
        // double focalLength = 0.0023;
        // double realHeight = 0.0508;
        // double imageHeight = 0; // Temprary
        // double objectHeight = locations[7] - locations[11];
        // double sensorHeight = 0; // Temporary

        //double distance = (focalLength * realHeight * imageHeight) / (objectHeight * sensorHeight);
        
    }

    public double findDistance(double realHeight, double imageHeight, double objectHeight, double sensorHeight) {
        double focalLength = 0.0023;

        return (focalLength * realHeight * imageHeight) / (objectHeight * sensorHeight);
    }
    
    private int reconvertInt(byte[] raw) {
        return (int)raw[3] << 24 + (int)raw[2] << 16 + (int)raw[1] << 8 + (int)raw[0];
    }
    
    private int reconvertDouble(byte[] raw) {
        return (int)raw[7] << 56 + (int)raw[6] << 48 + (int)raw[5] << 40 + (int)raw[4] << 32 +
                (int)raw[3] << 24 + (int)raw[2] << 16 + (int)raw[1] << 8 + (int)raw[0];
    } 
}

/*
new Thread(() -> {
            UsbCamera mainCamera = CameraServer.getInstance().startAutomaticCapture();
            mainCamera.setResolution(320, 240);
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Filtered", 320, 240);

            Mat source = new Mat();
            Mat filtered = new Mat();
            Mat output = new Mat();

            while(!Thread.interrupted()) {
                if (cvSink.grabFrame(source)==0) {
                    continue;
                }
                Core.inRange(source, new Scalar(0, 0, 0), new Scalar(0, 255, 0), filtered);
                Imgproc.cvtColor(filtered, output, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output);
            }
        }).start();
*/

