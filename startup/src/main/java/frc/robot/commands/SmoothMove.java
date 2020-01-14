/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * An example command that uses an example subsystem.
 */
public class SmoothMove extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSystem driveSystem;

  private Timer timer;

  double time, leftSpeed, rightSpeed;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SmoothMove(DriveSystem driveSystem, double time, double leftSpeed, double rightSpeed) {
    this.driveSystem = driveSystem;

    this.time = time;
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;

    timer = new Timer();
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
    double now = timer.get();

    if(now <= time*.25) {
      driveSystem.drive(now*leftSpeed, now*rightSpeed);
    } else if(now <= time*.75) {
      driveSystem.drive(leftSpeed, rightSpeed);
    } else {
      driveSystem.drive((time-now)*leftSpeed, (time-now)*rightSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSystem.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() >= time)
      return true;
    return false;
  }
}
