package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterBackward extends CommandBase {

    private Shooter shooter;
    private double speed;

    public ShooterBackward(Shooter shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
    }

    public void initialize() {
        shooter.reverseShooter(speed);
    }
}