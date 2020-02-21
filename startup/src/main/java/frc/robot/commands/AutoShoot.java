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
        timer.start();
    }

    public void initalize() {
        System.out.println("AutoShoot initalized");
        shooter.runShooter(speed);
    }

    public void execute() {
        shooter.runShooter(speed);
        // If the current time is more than or equal to 1 and the remainder of the current time is less than .5 seconds, stops index
        if(timer.get() >= 1 && timer.get()%2 < .5) {
            intake.runIndex(0);
        }
        // Otherwise if the current time is more than or equal to 1, run the index 
        else if(timer.get() >= 1) {
            intake.runIndex(speed);
        }
        System.out.println("AutoShoot running||timer.get()=="+ timer.get());
    }

    public boolean isFinished() {
        /* if(timer.get() >= balls*2+1) {
            return true;
        } */
        if(timer.get() >= 7) {
            return true;
        }
        return false;
    }
}