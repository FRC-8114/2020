package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Wait extends CommandBase {
    private Timer timer;
    private double seconds;

    public Wait(double seconds) {
        this.seconds = seconds;
        timer = new Timer();
        timer.start();
    }

    public boolean isFinished() {
        if(timer.get() >= seconds) {
            return true;
        }
        return false;
    }
}