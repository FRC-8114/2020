package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSystem;

public class IntakeBalls extends CommandBase {
    private IntakeSystem intake;
    private double speed;

    public IntakeBalls(IntakeSystem intake, double speed) {
        this.intake = intake;
        this.speed = speed;
    }

    public void initalize() {
        intake.runIntake(speed);
        intake.runIndex(speed);
    }

    public void end() {

    }

    public boolean isFinished() {
        return true;
    }
}