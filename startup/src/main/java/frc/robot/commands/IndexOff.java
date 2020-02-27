package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSystem;

public class IndexOff extends CommandBase {
    private IntakeSystem index;

    public IndexOff(IntakeSystem index) {
        this.index = index;
    }

    public void initalize() {
        index.runIndex(0);
    }

    public void execute() {
        index.runIndex(0);
    }

    public boolean isFinished() {
        return true;
    }
}