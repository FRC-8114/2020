package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSystem;

public class AngleShooter extends CommandBase {
    private ShooterSystem shooter;
    private double speed;

    public AngleShooter(ShooterSystem shooter, double speed) {
        addRequirements(shooter);
        this.shooter = shooter;
        this.speed = speed;
    }

    public void initalize() {
        shooter.setShooterPitch(speed);
    }

    public void end() {
        shooter.setShooterPitch(0);
    }

    public boolean isFinished() {
        return false;
    }
}