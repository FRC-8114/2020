package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSystem;

public class ShooterOff extends CommandBase {
    private ShooterSystem shooter;

    public ShooterOff(ShooterSystem shooter) {
        this.shooter = shooter;
    }

    public void initalize() {
        shooter.runShooter(0);
    }

    public void execute() {
        shooter.runShooter(0);
    }

    public boolean isFinished() {
        return true;
    }
}