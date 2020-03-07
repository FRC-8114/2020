package frc.robot.commands;

import frc.robot.subsystems.Index;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexStop extends CommandBase {

    private Index index;

    public IndexStop(Index index) {
        this.index = index;
    }

    public void initialize() {
        index.runIndex(0);
    }

    public boolean isFinished() {
        return true;
    }
}