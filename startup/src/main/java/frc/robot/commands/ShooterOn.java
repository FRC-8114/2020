package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSystem;

public class ShooterOn extends CommandBase {
    private ShooterSystem shooter;

    public ShooterOn(ShooterSystem shooter) {
        this.shooter = shooter;
    }

    public void initalize() {
        shooter.runShooter(.8);
    }

    public void execute() {
        
    }

    public boolean isFinished() {
        return true;
    }
}