package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSystem;

public class Wait extends CommandBase {
    private double time;
    private Timer timer;

    public Wait(double time) {
        this.time = time;
        timer = new Timer();
        timer.start();
    }

    public boolean isFinished() {
        if(timer.get() < time) {
            return false;
        }
        return true;
    }
}