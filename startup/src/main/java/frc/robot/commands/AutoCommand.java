package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.*;

public class AutoCommand {
    DriveSystem driveSystem;
    IntakeSystem intakeSystem;
    ShooterSystem shooterSystem;

    Timer timer;

    public AutoCommand(DriveSystem driveSystem, IntakeSystem intakesystem, ShooterSystem shooterSystem) {
        this.driveSystem = driveSystem;
        this.intakeSystem = intakeSystem;
        this.shooterSystem = shooterSystem;

        timer = new Timer();
    }

    public void initalize() {
        timer.start();
        timer.reset();
        driveSystem.drive(0, 0);
        intakeSystem.runIndex(0);
        shooterSystem.runShooter(0);
    }

    public void execute() {
        if(timer.get() <= 6) {
            driveSystem.drive(0, 0);
            shooterSystem.runShooter(.8);
            if(!(timer.get() <= 1)) {
                intakeSystem.runIndex(.8);
            }
        } else {
            shooterSystem.runShooter(0);
            intakeSystem.runIndex(0);
            driveSystem.drive(-.75, -.75);
        }
    }

    public void end() {
        driveSystem.drive(0, 0);
        intakeSystem.runIndex(0);
        shooterSystem.runShooter(0);
    }

    public boolean isFinished() {
        if(timer.get() >= 10) {
            return true;
        }
        return false;
    }
}