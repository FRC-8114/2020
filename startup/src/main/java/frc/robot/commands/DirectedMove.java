package frc.robot.commands;

import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DirectedMove extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private DriveSystem driveSystem;
    private OdometrySubsystem odometrySubsystem;
    private double speed, length;

    public DirectedMove(DriveSystem driveSystem, OdometrySubsystem odometrySubsystem,
                        double speed, double length) {
        this.driveSystem = driveSystem;
        this.odometrySubsystem = odometrySubsystem;
        this.speed = speed;
        this.length = length;

        addRequirements(driveSystem);
        addRequirements(odometrySubsystem);
    }

    @Override
    public void initialize() {
        odometrySubsystem.resetDriveEncoders(); //reset current distance
    }

    @Override
    public void execute() {
        double distance = odometrySubsystem.getDriveDistance();
        
        /*
            where 
                y is speed to the motors 
                x is distance traveled detected by the encoders
                z is top speed needed
                a is distance needed
                ... implies previous steps
            y = (z(x/a*10)+0.5)*0.99999
            explain
                x/a get percentage of distance traveled 
                *10 turn the range of 0.0 - 0.1 to 0.0 - 1.0
                z(...) get part of speed to ramp up
                +0.5 offset
                *0.99999 guards from going over 0.1
        */
        if(distance / length < 0.05) {
            driveSystem.drive((speed * ((distance / length) * 10.0) + 0.5) * 0.99999,
                              (speed * ((distance / length) * 10.0) + 0.5) * 0.99999);
        }
        /*
            where 
                y is speed to the motors 
                x is distance traveled detected by the encoders
                z is top speed needed
                a is distance needed
                ... implies previous steps
            y = (z - z(0.05 - ((x/a)-.95))+0.5)*0.99999
            explain
                x/a - 0.95 make range 0.95 - 1.0 to 0.0 - 0.05
                0.05 - (...)
         */
        else if(distance / length > .95) {
            driveSystem.drive((speed - speed * (0.05 - ((distance / length) - 0.95)) / 0.05 + 0.5) * 0.99999,
                              (speed - speed * (0.05 - ((distance / length) - 0.95)) / 0.05 + 0.5) * 0.99999);
        } else {
            driveSystem.drive(speed, speed);
        }
    }

    @Override
    public void end(boolean b) {

    }

    public boolean isFinished() {
        return false;
    }
}