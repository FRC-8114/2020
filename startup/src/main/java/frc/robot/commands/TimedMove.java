package frc.robot.commands;

import frc.robot.subsystems.DriveSystem;
//import frc.robot.subsystems.OdometrySubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class TimedMove extends CommandBase {
  private final DriveSystem driveSystem;

  //private final OdometrySubsystem odometrySystem;

  private Timer timer;

  double time, now, leftSpeed, rightSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TimedMove(DriveSystem driveSystem/*, OdometrySubsystem odometrySystem*/, double time, double leftSpeed, double rightSpeed) {
    this.driveSystem = driveSystem;
    this.time = time;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    //this.odometrySystem = odometrySystem;
    System.out.println("TimedMove created");

    timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSystem/*, odometrySystem*/);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("TimedMove initalized");
    timer.start();
    //odometrySystem.resetDriveEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    now = timer.get();
    if(now <= time*.25) {
      driveSystem.drive(now*leftSpeed, now*rightSpeed);
    } else if(now <= time*.75) {
      driveSystem.drive(leftSpeed, rightSpeed);
    } else {
      driveSystem.drive((time-now)*leftSpeed, (time-now)*rightSpeed);
    }
    System.out.println("Timed move running");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSystem.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() >= time) {
        //System.out.println("The current speed is: " + odometrySystem.getDriveDistance() / time);
        return true;
    }
    return false;
  }
}