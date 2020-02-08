package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSystem extends SubsystemBase{
    private WPI_VictorSPX intake, index;

    public IntakeSystem() {
        intake = new WPI_VictorSPX(15);
        index = new WPI_VictorSPX(16);
    }

    /**
     * Runs the intake 
     * 
     * @param speed     the speed at which to run
     */
    public void runIntake(double speed) {
        intake.set(speed);
    }

    /**
     * Runs the index
     * 
     * @param speed     the speed at which to run
     */
    public void runIndex(double speed) {
        index.set(speed);
    }
}