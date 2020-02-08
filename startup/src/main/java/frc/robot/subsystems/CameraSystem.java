package frc.robot.subsystems;

import java.lang.reflect.Array;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.*;
import edu.wpi.first.networktables.*;

import frc.robot.subsystems.NetworkSystem;

import org.opencv.imgproc.*;
import org.opencv.core.*;

public class CameraSystem extends SubsystemBase{
    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry points;

    private double[] locations;

    /*
    * Temp
    */
    private double[] minPos = new double[2], maxPos = new double[2];

    public CameraSystem() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("");

        points = table.getEntry("points");
        locations = new double[12];

        minPos = table.getEntry("minPos").getDoubleArray(new double[2]);
        maxPos = table.getEntry("maxPos").getDoubleArray(new double[2]);
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
        double focalLength = 0.0023;
        double realHeight = 0.0508;
        double imageHeight = 0; // Temprary
        double objectHeight = locations[7] - locations[11];
        double sensorHeight = 0; // Temporary

        double distance = (focalLength * realHeight * imageHeight) / (objectHeight * sensorHeight);
        
    }

    public double findDistance(double realHeight, double imageHeight, double objectHeight, double sensorHeight) {
        double focalLength = 0.0023;

        return (focalLength * realHeight * imageHeight) / (objectHeight * sensorHeight);
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