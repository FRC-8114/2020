package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSystem;

public class ShooterOn extends CommandBase {
    private ShooterSystem shooter;
    private double speed;

    public ShooterOn(ShooterSystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
    }

    public void initalize() {
    }

    public void execute() {
        shooter.runShooter(speed); 
    }

    public boolean isFinished() {
        return true;
    }
}