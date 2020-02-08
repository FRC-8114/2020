package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class RunSpinner extends CommandBase {
    private WheelOfMisfortuneSystem womSystem;
    private double speed;

    public RunSpinner(WheelOfMisfortuneSystem womSystem, double speed) {
        this.womSystem = womSystem;
        this.speed = speed;
    }

    public void initalize() {
        womSystem.runSpinner(speed);
    }

    public void execute() {

    }

    public void end() {
        womSystem.runSpinner(0);
    }

    public boolean isFinished() {
        return true;
    }
}