package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Encoder;

public class OdometrySubsystem extends SubsystemBase {
    private Encoder leftEncoder, rightEncoder;

    public OdometrySubsystem() {
        leftEncoder = new Encoder(0, 1);
        rightEncoder = new Encoder(2, 3);
        leftEncoder.setDistancePerPulse(1./WheelCircumference(6));
        rightEncoder.setDistancePerPulse(1./WheelCircumference(6));
    }

    public double InchesToMeters (double inches) {
        return inches /= 39.37;
    }

    public double WheelCircumference (double diameter) {
        return Math.PI*InchesToMeters(diameter);
    }
}