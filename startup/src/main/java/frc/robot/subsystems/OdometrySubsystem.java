package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class OdometrySubsystem extends SubsystemBase {
    private Encoder leftEncoder, rightEncoder;

    public OdometrySubsystem() {
        //rightEncoder = new Encoder(6, 7);
        leftEncoder = new Encoder(8,9, true, EncodingType.k1X); // Inverse

        leftEncoder.setDistancePerPulse(WheelCircumference(6));
        //rightEncoder.setDistancePerPulse(WheelCircumference(6));
        // Configures the encoders to recognize one rotation per circumference length
    }

    public void periodic() {
    }

    public double InchesToMeters (double inches) {
        return inches / 39.37;
        // Converts an inch value to meters
    }

    public double WheelCircumference (double diameter) {
        return Math.PI*InchesToMeters(diameter);
        // Calculates the circumference of the wheel by multiplying pi by the diameter in meters
    }

    public double GetDistancePerPulse() {
        return leftEncoder.getDistancePerPulse();
    }

    public void resetDriveEncoders() {
        leftEncoder.reset();
        //rightEncoder.reset();
    }

    public double getDriveDistance() {
        return leftEncoder.getDistance();
    }
}