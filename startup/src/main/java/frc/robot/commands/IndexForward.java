package frc.robot.commands;

import frc.robot.subsystems.Index;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexForward extends CommandBase {

    private Index index;
    private double speed;

    public IndexForward(Index index, double speed) {
        this.index = index;
        this.speed = speed;
    }

    public void initialize() {
        index.runIndex(speed);
        System.out.println("IndexForward");
    }

    public boolean isFinished() {
        return true;
    }
}