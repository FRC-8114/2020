package frc.robot.commands;

import frc.robot.subsystems.Spinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinnerForward extends CommandBase {

    private Spinner spinner;

    public SpinnerForward(Spinner spinner) {
        this.spinner = spinner;
    }

    public void initialize(double speed) {
        spinner.runSpinner(speed);
    }
}