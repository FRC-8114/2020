package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSystem;

public class ShooterLowOn extends CommandBase {
    private ShooterSystem shooter;

    public ShooterLowOn(ShooterSystem shooter) {
        this.shooter = shooter;
    }

    public void execute() {
        shooter.runShooter(.3); 
    }

    public boolean isFinished() {
        return true;
    }
}