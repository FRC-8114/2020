package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterStop extends CommandBase {

    private Shooter shooter;

    public ShooterStop(Shooter shooter) {
        this.shooter = shooter;
    }

    public void initialize() {
        shooter.runShooter(0);
    }
}