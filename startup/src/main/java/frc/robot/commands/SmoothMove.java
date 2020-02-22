/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.OdometrySubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;

/**
 * An example command that uses an example subsystem.
 */
public class SmoothMove extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSystem driveSystem;
  private OdometrySubsystem odometrySubsystem;

  private Encoder left_encoder, right_encoder;

  private Timer timer;

  private double distance;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SmoothMove(DriveSystem driveSystem, OdometrySubsystem odometrySubsystem, double distance) {
    this.driveSystem = driveSystem;
    this.odometrySubsystem = odometrySubsystem;

    this.distance = distance;

    timer = new Timer();
    left_encoder = new Encoder(0,1);
    right_encoder = new Encoder(2,3);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double current = odometrySubsystem.getDriveDistance();
    System.out.println("Current encoder count is = " + current);
    if(current <= 0.5*distance)
      driveSystem.drive(2*(current/distance), 2*(current/distance));
    else if(current <= distance)
      driveSystem.drive((distance-current)/distance, (distance-current)/distance);

    /*
    if(now <= time*.25) {
      driveSystem.drive(now*leftSpeed, now*rightSpeed);
    } else if(now <= time*.75) {
      driveSystem.drive(leftSpeed, rightSpeed);
    } else {
      driveSystem.drive((time-now)*leftSpeed, (time-now)*rightSpeed);
    }
    */
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSystem.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(odometrySubsystem.getDriveDistance() >= distance)
      return true;
    return false;
  }
}
