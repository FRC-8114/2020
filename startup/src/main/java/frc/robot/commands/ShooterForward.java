package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterForward extends CommandBase {

    private Shooter shooter;
    private double speed;

    public ShooterForward(Shooter shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
    }

    public void initialize() {
        System.out.println("Shooter Forward");
        shooter.runShooter(speed);
    }
}