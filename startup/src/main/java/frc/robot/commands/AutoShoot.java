package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class AutoShoot extends CommandBase {
    private ShooterSystem shooter;
    private IntakeSystem intake;
    private double speed;
    //private int balls;
    private Timer timer;

    public AutoShoot(ShooterSystem shooter, IntakeSystem intake, double speed/*, int balls*/) {
        System.out.println("AutoShoot created");
        this.shooter = shooter;
        this.intake = intake;
        this.speed = speed;
        //this.balls = balls;
        timer = new Timer();
        addRequirements(shooter, intake);
    }

    public void initialize() {
        timer.start();
    } 

    public void execute() {
        shooter.runShooter(speed);
        if(timer.get() <= 1) {
            intake.runIndex(0);
        }
        else {
            intake.runIndex(speed);
        }
    }

    public void end(boolean interrupted) {
        intake.runIndex(0);
        shooter.runShooter(0);
    }

    public boolean isFinished() {
        /* if(timer.get() >= balls*2+1) {
            return true;
        } */
        if(timer.get() >= 3) {
            return true;
        }
        return false;
    }
}