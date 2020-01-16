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

            Imgproc imgProc = new Imgproc();

            Mat source = new Mat();
            //Mat filtered = new Mat();
            Mat output = new Mat();

            while(!Thread.interrupted()) {
                if (cvSink.grabFrame(source)==0) {
                    continue;
                }
                imgProc.applyColorMap(source, output, imgProc.COLORMAP_COOL);
                outputStream.putFrame(output);
            }
        }).start();
    }
}