package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.cameraserver.CameraServer;
import org.opencv.core.*;
import edu.wpi.cscore.*;
import org.opencv.imgproc.*;

public class CameraSystem extends SubsystemBase{
    public CameraSystem() {
        new Thread(() -> {
            UsbCamera mainCamera = CameraServer.getInstance().startAutomaticCapture();
            mainCamera.setResolution(320, 240);
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 320, 240);

            Mat source = new Mat();
            Mat filtered = new Mat();
            Mat output = new Mat();

            while(!Thread.interrupted()) {
                if (cvSink.grabFrame(source)==0) {
                    continue;
                }
                for(int x = 0; x < source.rows(); x++) {
                    for(int y = 0; y < source.cols(); y++) {
                        double[] proc = source.get(x, y);
                        proc[0] = 0; // BLUE
                        proc[2] = 0; // RED
                        filtered.put(x, y, proc);
                    }
                }
                Imgproc.cvtColor(filtered, output, Imgproc.COLOR_BGR2GRAY);
                outputStream.putFrame(output);
            }
        }).start();
    }
}