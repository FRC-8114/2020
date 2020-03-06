package frc.robot.commands;

import frc.robot.subsystems.Spinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinnerStop extends CommandBase {

    private Spinner spinner;

    public SpinnerStop(Spinner spinner) {
        this.spinner = spinner;
    }

    public void initialize() {
        spinner.runSpinner(0);
    }

    public boolean isFinished() {
        return true;
    }
}