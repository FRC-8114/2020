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
        kicker = new WPI_VictorSPX(14);
        shooterRunning = false;
        System.out.println("Shooter Subsytem created.");
    }

    /**
     * Sets the pitch, currently just runs the shooterPitch motor
     * 
     * @param speed    the speed at which to run the motor
     */
    public void setShooterPitch(double speed) {
        System.out.println("ShooterSystem: shooterPitch speed set to "+ speed +".");
        shooterPitch.set(speed);
    }

    /**
     * Runs the shooter wheels
     * 
     * @param speed     the speed to run the shooter at
     */
    public void runShooter(double speed) {
        shooterLeft.set(speed);
        shooterRight.set(speed);
        if(speed != 0)
            shooterRunning = true;
        else
            shooterRunning = false;
    }

    /**
     * Sets the kickers speed
     * 
     * @param speed     the speed to run the kicker at
     */
    public void runKicker(double speed) {
        kicker.set(speed);
    }

    
    public boolean isShooterRunning() {
        return shooterRunning;
    }
}