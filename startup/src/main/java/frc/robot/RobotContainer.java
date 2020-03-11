/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.autonomous_groups.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  private final Intake intake;
  private final Index index;
  private final Climber climber;
  private final DriveTrain drive;
  private final Shooter shooter;
  private final Network network;

  private final ShootAndMove auto;
  
  private final XboxController controller;
  private JoystickButton lb, rb;
  private Trigger lt, rt;

  public double shootHigh, shootLow, indexHigh, indexLow, intakeSpeed, climbUp, climbDown;
  
  public RobotContainer(XboxController controller) {
    this.controller = controller;

    shootHigh = .6;
    shootLow = .3;
    indexHigh = .6;
    indexLow = .3;
    intakeSpeed = .8;
    climbUp = .6;
    climbDown = .4;

    intake = new Intake();
    index = new Index();
    climber = new Climber();
    drive = new DriveTrain(controller);
    shooter = new Shooter();
    network = new Network();

    auto = new ShootAndMove(drive, shooter, intake, index);

    configureButtonBindings();
    configureShuffleBoardButtons();
  }

  public void configureShuffleBoardButtons() {
    /* SmartDashboard Buttons for Shooter Control */
    SmartDashboard.putData("Shooter run high", new ShooterForward(shooter, shootHigh));
    SmartDashboard.putData("Shooter run low", new ShooterForward(shooter, shootLow));
    SmartDashboard.putData("Shooter stop", new ShooterStop(shooter));

    /* SmartDashboard Buttons for Index Control */
    SmartDashboard.putData("Run index", new IndexForward(index, indexLow));
    SmartDashboard.putData("Index stop", new IndexStop(index));

    /* SmartDashboard Buttons for Climber */
    SmartDashboard.putData("Climber raise", new ClimberUp(climber, climbUp));
    SmartDashboard.putData("Climber lower", new ClimberDown(climber, climbDown));
    SmartDashboard.putData("Climber raise", new ClimberStop(climber));

    /* SmartDashborad Buttons for Updating Values */
    SmartDashboard.putData("Update Values", new NetworkUpdateValues(network));
  }


  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void configureButtonBindings() {
    lb = new JoystickButton(controller, 5);
    rb = new JoystickButton(controller, 6);
    lt = new Trigger(new BooleanSupplier() {
      @Override
      public boolean getAsBoolean() {
        return controller.getTriggerAxis(Hand.kLeft) == 1;
      }});
    rt = new Trigger(new BooleanSupplier() {
      @Override
      public boolean getAsBoolean() {
        return controller.getTriggerAxis(Hand.kRight) == 1;
      }});

    // Runs the intake
    lt.whenActive(new IntakeForward(intake, intakeSpeed));
    lt.whenInactive(new IntakeStop(intake));

    // Runs the index
    rt.whenActive(new IndexForward(index, indexHigh));
    rt.whenInactive(new IndexStop(index));

    // Reverses the intake
    lb.whileHeld(new IntakeBackward(intake, intakeSpeed));
    lb.whenReleased(new IntakeStop(intake));

    // Reverses the index
    rb.whileHeld(new IndexBackward(index, indexHigh));
    rb.whenReleased(new IndexStop(index));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return auto;
  }


  //Getters
  public DriveTrain getDrive() {
    return drive;
  }
}