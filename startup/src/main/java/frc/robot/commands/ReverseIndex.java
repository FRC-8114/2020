package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSystem;

public class ReverseIndex extends CommandBase {
    private IntakeSystem intake;
    private double speed;

    public ReverseIndex(IntakeSystem intake, double speed) {
        this.intake = intake;
        this.speed = speed;
    }

    public void initalize() {
        intake.reverseIndex(speed);
    }

    public void end() {
        intake.runIndex(0);
    }

    public boolean isFinished() {
        return true;
    }
}