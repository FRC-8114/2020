package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSystem;

public class IndexOn extends CommandBase {
    private IntakeSystem index;
    private double speed;

    public IndexOn(IntakeSystem index, double speed) {
        this.index = index;
        this.speed = speed;
    }

    public void initalize() {
    }

    public void execute() {
        index.runIndex(speed);
    }

    public boolean isFinished() {
        return true;
    }
}