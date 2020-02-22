/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
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
* This class is where the bulk of the robot should be declared. Since Command-based is a
* "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
* periodic methods (other than the scheduler calls). Instead, the structure of the robot
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

  private final XboxController controllerA,controllerB;
  private static JoystickButton a1,a2,b1,b2,x1,x2,y1,y2,back,start;

  private static Timer timer;
  private static boolean shooting;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controllerA, XboxController controllerB, PowerDistributionPanel pdp) {
    driveSystem = new DriveSystem(controllerA);
    cameraSystem = new CameraSystem();
    odometrySystem = new OdometrySubsystem();
    networkSystem = new NetworkSystem();
    powerSystem = new PowerSystem(pdp);
    shooterSystem = new ShooterSystem();
    wheelOfMisfortuneSystem = new WheelOfMisfortuneSystem();
    intakeSystem = new IntakeSystem();
    climberSystem = new ClimberSystem();
    this.controllerA = controllerA;
    this.controllerB = controllerB;

    firstMove = new SmoothMove(driveSystem, odometrySystem, 4);

    timer = new Timer();
    shooting = false;
    // Configure the button bindings
    controllerA_configureButtonBindings();
    controllerB_configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void controllerA_configureButtonBindings() {
    a1 = new JoystickButton(controllerA, 1);
    // b = new JoystickButton(controller1, 2);
    x1 = new JoystickButton(controllerA, 3);
    y1 = new JoystickButton(controllerA, 4);
    // back = new JoystickButton(controller, 7); Not used currently
    // start = new JoystickButton(controller, 8); Not used currently
  
    // b.whileHeld(() -> intakeSystem.reverseIndex(.5)).whenReleased(() -> intakeSystem.runIndex(0));
    y1.whileHeld(() -> wheelOfMisfortuneSystem.extendArm(.2)); // Raise arm
    y1.whenReleased(() -> wheelOfMisfortuneSystem.extendArm(0));
    x1.whileHeld(new RunSpinner(wheelOfMisfortuneSystem, .4)).whenReleased(new RunSpinner(wheelOfMisfortuneSystem, 0)); // Toggle color wheel spinner
    a1.whileHeld(() -> wheelOfMisfortuneSystem.retractArm(.2)); // Lower arm
    a1.whenReleased(() -> wheelOfMisfortuneSystem.extendArm(0));
  }
  
  public void controllerB_configureButtonBindings() {
    a2 = new JoystickButton(controllerB, 1);
    // b = new JoystickButton(controllerB, 2);
    x2 = new JoystickButton(controllerB, 3);
    y2 = new JoystickButton(controllerB, 4);
    // back = new JoystickButton(controller, 7); Not used currently
    // start = new JoystickButton(controller, 8); Not used currently
  
    // b.whileHeld(() -> intakeSystem.reverseIndex(.5)).whenReleased(() -> intakeSystem.runIndex(0));
    y2.whileHeld(() -> shooterSystem.setShooterPitch(.2));
    y2.whenReleased(() -> shooterSystem.setShooterPitch(0)); // Raise climber
    x2.whileHeld(() -> intakeSystem.reverseIndex(.2));
    x2.whenReleased(() -> intakeSystem.reverseIndex(0)); // Reverse Index
    a2.whileHeld(() -> shooterSystem.setShooterPitch(-.2));
    a2.whenReleased(() -> shooterSystem.setShooterPitch(0)); // Lower climber
  }
  
  public void periodic() {
    if(controllerA.getTriggerAxis(Hand.kLeft) == 1) {
      intakeSystem.runIntake(.6);
      intakeSystem.runIndex(.6);
    }

    if(controllerA.getBumper(Hand.kRight) == true) {
      System.out.println("Working");
      new DriveForward(driveSystem, .5);
    }
  
    if(controllerB.getBumper(Hand.kRight) == true) {
      intakeSystem.runIndex(.6);
    }

    if(controllerB.getTriggerAxis(Hand.kLeft) == 1) {
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
    if(controllerB.getTriggerAxis(Hand.kRight) != 1 && controllerB.getBumper(Hand.kRight) != true) {
      shooterSystem.runShooter(0);
      shooterSystem.runKicker(0);
      intakeSystem.runIndex(0);
      intakeSystem.runIntake(0);
      timer.stop();
      shooting = false;
    }
  
    if(controllerB.getPOV()==360 || controllerB.getPOV()==0) {
      climberSystem.extendClimber(.2);
    }
    if(controllerB.getPOV()==180) {
      climberSystem.retractClimber(.2);
    }
    if(controllerB.getPOV() != 360 && controllerB.getPOV() != 180)
      wheelOfMisfortuneSystem.extendArm(0);
  
    if(controllerB.getBumper(Hand.kLeft) == true) {
      shooterSystem.runShooter(.6);
    }
  
  if(controllerB.getTriggerAxis(Hand.kRight) != 1 && controllerB.getBumper(Hand.kRight) != true) {
    shooterSystem.runShooter(0);
    shooterSystem.runKicker(0);
    intakeSystem.runIndex(0);
    intakeSystem.runIntake(0);
    timer.stop();
    shooting = false;
  }

  if(controllerB.getPOV()==360 || controllerB.getPOV()==0)
    climberSystem.extendClimber(.2);
  if(controllerB.getPOV()==180)
    climberSystem.retractClimber(.2);
  if(controllerB.getPOV() != 360 && controllerB.getPOV() != 180)
    wheelOfMisfortuneSystem.extendArm(0);

  if(controllerB.getBumper(Hand.kRight) == true) {
    shooterSystem.runShooter(.6);
  }
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