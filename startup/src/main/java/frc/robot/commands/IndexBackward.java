package frc.robot.commands;

import frc.robot.subsystems.Index;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexBackward extends CommandBase {

    private Index index;
    private double speed;

    public IndexBackward(Index index, double speed) {
        this.index = index;
        this.speed = speed;
    }

    public void initialize() {
        index.reverseIndex(speed);
    }
}