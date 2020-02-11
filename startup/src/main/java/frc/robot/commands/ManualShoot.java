package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class ManualShoot extends CommandBase {
    private ShooterSystem shooter;
    private IntakeSystem intake;
    private double speed;

    public ManualShoot(ShooterSystem shooter, IntakeSystem intake, double speed) {
        this.shooter = shooter;
        this.intake = intake;
        this.speed = speed;
    }

    public void initalize() {
        shooter.runKicker(speed);
        intake.runIndex(speed);
        intake.runIntake(speed);
    }

    public void execute() {

    }

    public void end() {
        shooter.runKicker(0);
        intake.runIndex(0);
        intake.runIntake(0);
    }

    public boolean isFinished() {
        return true;
    }
}