package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Encoder;

public class OdometrySubsystem extends SubsystemBase {
    private Encoder leftEncoder, rightEncoder;

    public OdometrySubsystem() {
        leftEncoder = new Encoder(0, 1);
        rightEncoder = new Encoder(2, 3);
        // Creates two Encoder objects

        leftEncoder.setDistancePerPulse(WheelCircumference(6));
        rightEncoder.setDistancePerPulse(WheelCircumference(6));
        // Configures the encoders to recognize one rotation per circumference length

        GetDistance();
    }

    public double InchesToMeters (double inches) {
        return inches /= 39.37;
        // Converts an inch value to meters
    }

    public double WheelCircumference (double diameter) {
        return Math.PI*InchesToMeters(diameter);
        // Calculates the circumference of the wheel by multiplying pi by the diameter in meters
    }

    public double GetDistance() {
        return leftEncoder.getDistance();
    }

    public double GetDistancePerPulse() {
        return leftEncoder.getDistancePerPulse();
    }

    public void resetDriveEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public double getDriveDistance() {
        return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
    }
}