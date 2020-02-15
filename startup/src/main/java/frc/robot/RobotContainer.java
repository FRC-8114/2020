/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
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

  private static Timer timer;
  private static boolean shooting;

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

    firstMove = new SmoothMove(driveSystem, odometrySystem, 4);

    timer = new Timer();
    shooting = false;
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
    // back = new JoystickButton(controller, 7); Not used currently
    // start = new JoystickButton(controller, 8); Not used currently

    b.whileHeld(() -> intakeSystem.reverseIndex(.5)).whenReleased(() -> intakeSystem.runIndex(0));
    y.whileHeld(() -> shooterSystem.setShooterPitch(-.2)).whenReleased(() -> shooterSystem.setShooterPitch(0)); // Raise shooter angle
    x.whileHeld(() -> wheelOfMisfortuneSystem.runSpinner(.4)).whenReleased(() -> wheelOfMisfortuneSystem.runSpinner(0)); // Toggle color wheel spinner
    a.whileHeld(() -> shooterSystem.setShooterPitch(.2)).whenReleased(() -> shooterSystem.setShooterPitch(0)); // Lower shooter angle
  }


  public void periodic() {
    if(controller.getTriggerAxis(Hand.kLeft) == 1) {
      intakeSystem.runIndex(.6);
      intakeSystem.runIntake(1);
    }
    if(controller.getTriggerAxis(Hand.kRight) == 1) {
      if(!shooting) {
        shooterSystem.runShooter(.6);
        timer.reset();
        timer.start();
        shooting = true;
      }
      if(shooting && timer.get()>=0.5) {
        shooterSystem.runKicker(.2);
        intakeSystem.runIndex(.6);
        intakeSystem.runIntake(1);
      }
    }
    if(controller.getTriggerAxis(Hand.kRight) != 1 && controller.getTriggerAxis(Hand.kLeft) != 1) {
      shooterSystem.runShooter(0);
      shooterSystem.runKicker(0);
      intakeSystem.runIndex(0);
      intakeSystem.runIntake(0);
      timer.stop();
      shooting = false;
    }
    if(controller.getPOV()==360 || controller.getPOV()==0)
      wheelOfMisfortuneSystem.extendArm(.4);
    if(controller.getPOV()==90)
      climberSystem.retractClimber(.2);
    if(controller.getPOV()==180)
      wheelOfMisfortuneSystem.retractArm(.4);
    if(controller.getPOV()==270)
      climberSystem.extendClimber(.2);
    if(controller.getPOV() != 360 && controller.getPOV() != 180)
      wheelOfMisfortuneSystem.extendArm(0);
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
