package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeBackward extends CommandBase {

    private Intake intake;

    public IntakeBackward(Intake intake) {
        this.intake = intake;
    }

    public void initialize(double speed) {
        intake.reverseIntake(speed);
    }
}