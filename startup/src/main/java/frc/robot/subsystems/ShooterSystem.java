package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSystem extends SubsystemBase{
    private WPI_VictorSPX shooterLeft, shooterRight, shooterPitch, kicker;
    private boolean shooterRunning;

    public ShooterSystem() {
        shooterLeft = new WPI_VictorSPX(10);
        shooterRight = new WPI_VictorSPX(11);
        shooterPitch = new WPI_VictorSPX(9);
        shooterRunning = false;
    }

    /**
     * Sets the pitch, currently just runs the shooterPitch motor
     * 
     * @param speed    the speed at which to run the motor
     */
    public void setShooterPitch(double speed) {
        shooterPitch.set(speed);
    }

    /**
     * Runs the shooter wheels
     * 
     * @param speed     the speed to run the shooter at
     */
    public void runShooter(double speed) {
        shooterLeft.set(-speed);
        shooterRight.set(speed);
        if(speed != 0)
            shooterRunning = true;
        else
            shooterRunning = false;
    }
    
    public boolean isShooterRunning() {
        return shooterRunning;
    }
}