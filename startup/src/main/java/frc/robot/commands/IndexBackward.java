package frc.robot.commands;

import frc.robot.subsystems.Index;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexBackward extends CommandBase {

    private Index index;

    public IndexBackward(Index index) {
        this.index = index;
    }

    public void initialize(double speed) {
        index.reverseIndex(speed);
    }
}