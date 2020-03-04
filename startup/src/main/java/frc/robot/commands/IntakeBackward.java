package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeBackward extends CommandBase {

    private Intake intake;
    private double speed;

    public IntakeBackward(Intake intake, double speed) {
        this.intake = intake;
        this.speed = speed;
    }

    public void initialize() {
        intake.reverseIntake(speed);
    }
}