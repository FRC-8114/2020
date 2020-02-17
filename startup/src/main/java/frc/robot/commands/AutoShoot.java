package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class AutoShoot extends CommandBase {
    private ShooterSystem shooter;
    private IntakeSystem intake;
    private double speed;
    private Timer timer;

    public AutoShoot(ShooterSystem shooter, IntakeSystem intake, double speed) {
        this.shooter = shooter;
        this.intake = intake;
        this.speed = speed;
        timer = new Timer();
    }

    public void initalize() {
        shooter.runShooter(speed);
        intake.runIndex(speed);
    }

    public void execute() {
    }

    public boolean isFinished() {
        if(timer.get() >= 5)
            return true;
        return false;
    }
}