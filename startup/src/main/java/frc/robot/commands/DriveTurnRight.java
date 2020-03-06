package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveTurnRight extends CommandBase {
    private DriveTrain driveTrain;
    private Timer timer;
    private final double time, speed;

    public DriveTurnRight(DriveTrain driveTrain, double time, double speed) {
        this.driveTrain = driveTrain;
        this.time = time;
        this.speed = speed;
        driveTrain.resetEncoders();
        timer = new Timer();

        addRequirements(driveTrain);
    }

    public void execute() {
        if(timer.get() < time*.25) {
            driveTrain.turnRight((timer.get()/(time*.25))*speed, (timer.get()/(time*.25))*speed);
        } else if(timer.get() < time*.75) {
            driveTrain.turnRight(speed, speed);
        } else {
            driveTrain.turnRight(((timer.get()-time*.75)/(time*.25))*speed, ((timer.get()-time*.75)/(time*.25))*speed);
        }
    }

    public boolean isFinished() {
        if(timer.get() >= time) {
            return true;
        }
        return false;
    }
}