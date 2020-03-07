package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeForward extends CommandBase {

    private Intake intake;
    private double speed;

    public IntakeForward(Intake intake, double speed) {
        this.intake = intake;
        this.speed = speed;
    }

    public void initialize() {
        intake.runIntake(speed);
    }

    public boolean isFinished() {
        return true;
    }
}