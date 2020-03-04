package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterForward extends CommandBase {

    private Shooter shooter;

    public ShooterForward(Shooter shooter) {
        this.shooter = shooter;
    }

    public void initialize(double speed) {
        shooter.runShooter(speed);
    }
}