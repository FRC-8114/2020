package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeForward extends CommandBase {

    private Intake intake;

    public IntakeForward(Intake intake) {
        this.intake = intake;
    }

    public void initialize(double speed) {
        intake.runIntake(speed);
    }
}