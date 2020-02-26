package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSystem;

public class IndexOn extends CommandBase {
    private IntakeSystem index;

    public IndexOn(IntakeSystem index) {
        this.index = index;
    }

    public void initalize() {
        index.runIndex(.8);
    }

    public void execute() {
        
    }

    public boolean isFinished() {
        return true;
    }
}