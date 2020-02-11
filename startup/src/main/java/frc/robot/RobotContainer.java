/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj.XboxController.*;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSystem driveSystem;
  private final CameraSystem cameraSystem;
  private final OdometrySubsystem odometrySystem;
  private final NetworkSystem networkSystem;
  private final SmoothMove firstMove;
  private final PowerSystem powerSystem;
  private final ShooterSystem shooterSystem;
  private final WheelOfMisfortuneSystem wheelOfMisfortuneSystem;
  private final IntakeSystem intakeSystem;
  private final ClimberSystem climberSystem;

  private final XboxController controller;
  private static JoystickButton a,b,x,y,back,start;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controller, PowerDistributionPanel pdp) {
    driveSystem = new DriveSystem(controller);
    cameraSystem = new CameraSystem();
    odometrySystem = new OdometrySubsystem();
    networkSystem = new NetworkSystem();
    powerSystem = new PowerSystem(pdp);
    shooterSystem = new ShooterSystem();
    wheelOfMisfortuneSystem = new WheelOfMisfortuneSystem();
    intakeSystem = new IntakeSystem();
    climberSystem = new ClimberSystem();
    this.controller = controller;

    firstMove = new SmoothMove(driveSystem, 2, .8, .8);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void configureButtonBindings() {
    a = new JoystickButton(controller, 1);
    b = new JoystickButton(controller, 2);
    x = new JoystickButton(controller, 3);
    y = new JoystickButton(controller, 4);
    // back = new JoystickButton(controller, 7);
    // start = new JoystickButton(controller, 8);

    b.whenPressed(new IntakeBalls(intakeSystem, -.5)).whenReleased(new IntakeBalls(intakeSystem, 0)); // Reverses the index
    y.whenPressed(new AngleShooter(shooterSystem, -.2)).whenReleased(new AngleShooter(shooterSystem, 0)); // Raise shooter angle
    a.whenPressed(new AngleShooter(shooterSystem, .2)).whenReleased(new AngleShooter(shooterSystem, 0)); // Lower shooter angle
    x.whenPressed(new RunSpinner(wheelOfMisfortuneSystem, .4)).whenReleased(new RunSpinner(wheelOfMisfortuneSystem, 0)); // Toggle color wheel spinner
  }


  public void periodic() {
    /**
     * Triggers control the index, intake, shooter, and kicker wheel
     */
    if(controller.getTriggerAxis(Hand.kLeft) == 1) { // If left trigger is pressed, intake and index are runned
      new IntakeBalls(intakeSystem, .8).schedule();
    }
    if(controller.getTriggerAxis(Hand.kRight) == 1) { //Runs the shooter, kicker, index, and intake
      new ArmShooter(shooterSystem, .8).schedule(); //Starts shooter
      new IntakeBalls(intakeSystem, .8).schedule(); //Starts intake, index, kicker
      shooterSystem.runKicker(.8);
    }
    if(controller.getTriggerAxis(Hand.kRight) != 1 && controller.getTriggerAxis(Hand.kLeft) != 1) { //Stops the shooter, kicker, index, and intake if no relevant input is detected
      new ArmShooter(shooterSystem, 0).schedule(); 
      new IntakeBalls(intakeSystem, .8).schedule();
      shooterSystem.runKicker(0);
    }

    /**
     * DPad raising and lowering the robot via the climber
     */
    if(controller.getPOV() != 90 && controller.getPOV() != 270) //Stops the climber when no relevant input is read
      climberSystem.extendClimber(0);
    if(controller.getPOV()==90) //Extends the climber
      climberSystem.retractClimber(.2);
    if(controller.getPOV()==270) //Retracts the climber
      climberSystem.extendClimber(.2);

    /**
     * DPad raising and lowering of the arm
     */
    if(controller.getPOV() != 360 && controller.getPOV() != 180) //Stops the arm when no relevant input is read
      wheelOfMisfortuneSystem.raiseArm(0);
    if(controller.getPOV()==180) //Raises the arm
      wheelOfMisfortuneSystem.lowerArm(.4);
    if(controller.getPOV()==360 || controller.getPOV()==0) //Lowers the arm
      wheelOfMisfortuneSystem.raiseArm(.4);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return firstMove;
  }


  //Getters
}
