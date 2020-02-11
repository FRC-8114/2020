package frc.robot.commands;
/*
Could I make a method in here to stop the shooter or would that be the isfinished ok then

*/
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSystem;

public class ArmShooter extends CommandBase {
    private ShooterSystem shooter;
    private double speed;

    public ArmShooter(ShooterSystem shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
    }

    public void initialize() {
        shooter.runShooter(speed);
    }

    public boolean isFinished() {
        return true;
    }
}