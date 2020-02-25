package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSystem;

public class ArmShooter extends CommandBase {
    private ShooterSystem shooter;

    public ArmShooter(ShooterSystem shooter) {
        this.shooter = shooter;
    }

    public void initalize() {
        if(shooter.isShooterRunning())
            shooter.runShooter(0);
        else
            shooter.runShooter(1);
    }

    public void execute() {
        
    }

    public boolean isFinished() {
        return true;
    }
}