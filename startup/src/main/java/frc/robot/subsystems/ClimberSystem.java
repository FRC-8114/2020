package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSystem extends SubsystemBase {
    private WPI_VictorSPX climberLeft, climberRight;

    /**
     * Inits
     */
    public ClimberSystem () {
        climberLeft = new WPI_VictorSPX(23);
        climberRight = new WPI_VictorSPX(24);
    }


    public void setClimbSpeed(double speed) {
        climberLeft.set(speed);
        climberRight.set(speed);
    }
}