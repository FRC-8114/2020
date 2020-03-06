package frc.robot.commands;

import frc.robot.subsystems.Spinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinnerForward extends CommandBase {

    private Spinner spinner;
    private double speed;

    public SpinnerForward(Spinner spinner, double speed) {
        this.spinner = spinner;
        this.speed = speed;
    }

    public void initialize() {
        spinner.runSpinner(speed);
    }

    public boolean isFinished() {
        return true;
    }
}