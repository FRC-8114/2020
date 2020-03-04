package frc.robot.commands;

import frc.robot.subsystems.Spinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinnerBackward extends CommandBase {

    private Spinner spinner;

    public SpinnerBackward(Spinner spinner) {
        this.spinner = spinner;
    }

    public void initialize(double speed) {
        spinner.reverseSpinner(speed);
    }
}