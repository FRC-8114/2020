package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShooterBackward extends CommandBase {

    private Shooter shooter;

    public ShooterBackward(Shooter shooter) {
        this.shooter = shooter;
    }

    public void initialize(double speed) {
        shooter.reverseShooter(speed);
    }
}