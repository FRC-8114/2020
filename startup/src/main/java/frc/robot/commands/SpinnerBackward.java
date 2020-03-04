package frc.robot.commands;

import frc.robot.subsystems.Spinner;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SpinnerBackward extends CommandBase {

    private Spinner spinner;
    private double speed;

    public SpinnerBackward(Spinner spinner, double speed) {
        this.spinner = spinner;
        this.speed = speed;
    }

    public void initialize() {
        spinner.reverseSpinner(speed);
    }
}