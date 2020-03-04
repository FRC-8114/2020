package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeStop extends CommandBase {

    private Intake intake;

    public IntakeStop(Intake intake) {
        this.intake = intake;
    }

    public void initialize() {
        intake.runIntake(0);
    }
}