package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.*;

import java.util.Arrays;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class CameraSystem extends SubsystemBase{
    private double[] redouble;
    private GripPipeline gripPipeline;
    private I2C arduino;
    private double velocity, distance, angle, offset;
    private ShooterSystem shooter;
    private IntakeSystem intake;
    private DriveSystem drive;

    //private double[] minPos = new double[2], maxPos = new double[2];

    public CameraSystem() {
        gripPipeline = new GripPipeline();

        //minPos = table.getEntry("minPos").getDoubleArray(new double[2]);
        //maxPos = table.getEntry("maxPos").getDoubleArray(new double[2]);

        /*new Thread(() -> {
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

    public double[] getArduinoStuffs() {
        /**
         * order of recv
         * offset - int (4 bytes)
         * distance - int (4 bytes) */
        byte[] recv = new byte[4 + 4];
        arduino.readOnly(recv, 16);
        int[] arduinoParams = new int[2];
        arduinoParams[0] = reconvertInt(Arrays.copyOfRange(recv, 0, 3)); // Offset
        arduinoParams[1] = reconvertInt(Arrays.copyOfRange(recv, 4, 7)); // Distance
        redouble = new double[2];
        redouble[0] = (double)arduinoParams[0] / 1000;
        redouble[1] = (double)arduinoParams[1] / 1000;
        System.out.println(Arrays.toString(arduinoParams));
        return redouble;
        // redouble = offset, distance, angle, x, y
    }

    public void calculateVelocity() {
        getArduinoStuffs();
        distance = redouble[1];
        angle = redouble[2];
        velocity = (.5 * (1.0 / Math.cos(Math.toRadians(angle))) * Math.sqrt(Math.pow((Math.pow(distance, 2)) * (2 * 386.088583) * Math.tan(Math.toRadians(angle)), 2)));
        velocity /= (90 * Math.tan(Math.toRadians(angle)) - (98.25 - 24));
        shooter.runShooter(velocity);
        // Wait for 3 seconds
        intake.runIndex(.5);
    }

    public void adjustOffset() {
        getArduinoStuffs();
        offset = redouble[0];
        if (offset > 0.5) {
            drive.drive(-0.3, 0.3);
        }
        else if (offset < -0.5) {
            drive.drive(0.3, -0.3);
        }
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
    
    private int reconvertInt(byte[] raw) {
        return (int)raw[3] << 24 + (int)raw[2] << 16 + (int)raw[1] << 8 + (int)raw[0];
    }
    
    private int reconvertDouble(byte[] raw) {
        return (int)raw[7] << 56 + (int)raw[6] << 48 + (int)raw[5] << 40 + (int)raw[4] << 32 +
                (int)raw[3] << 24 + (int)raw[2] << 16 + (int)raw[1] << 8 + (int)raw[0];
    } 
}
