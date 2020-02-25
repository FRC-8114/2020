/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;

//import frc.robot.commands.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.button.*;
//import edu.wpi.first.wpilibj.XboxController.*;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {
  private final DriveSystem driveSystem;
  private final CameraSystem cameraSystem;
  private final OdometrySubsystem odometrySystem;
  //private final NetworkSystem networkSystem;
  private final Autonomous autonomous;
  //private final PowerSystem powerSystem;
  private final ShooterSystem shooterSystem;
  private final WheelOfMisfortuneSystem wheelOfMisfortuneSystem;
  private final IntakeSystem intakeSystem;
  private final ClimberSystem climberSystem;

  private final XboxController controllerA,controllerB;
  private static JoystickButton a1, a2, x1, x2, y1, y2;
  private static Timer timer;
  private static boolean shooting;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controllerA, XboxController controllerB/*, PowerDistributionPanel pdp*/) {
    // Initializes the various subsystems
    driveSystem = new DriveSystem(controllerA);
    cameraSystem = new CameraSystem();
    odometrySystem = new OdometrySubsystem();
    //networkSystem = new NetworkSystem();
    //powerSystem = new PowerSystem(pdp);
    shooterSystem = new ShooterSystem();
    wheelOfMisfortuneSystem = new WheelOfMisfortuneSystem();
    intakeSystem = new IntakeSystem();
    climberSystem = new ClimberSystem();

    // Initializes the various commmands
    autonomous = new Autonomous(driveSystem, odometrySystem, shooterSystem, intakeSystem); // In meters

    // Initializes others
    timer = new Timer();
    this.controllerA = controllerA;
    this.controllerB = controllerB;

    // Configure the button bindings for the two controllers
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
    // Initializes buttons for first XboxController
    a1 = new JoystickButton(controllerA, 1);
    x1 = new JoystickButton(controllerA, 3);
    y1 = new JoystickButton(controllerA, 4);
  
    // Raises the Intake Arm
    y1.whileHeld(() -> wheelOfMisfortuneSystem.extendArm(.4));
    y1.whenReleased(() -> wheelOfMisfortuneSystem.extendArm(0));

    // Runs the Color Spinner
    x1.whileHeld(() -> wheelOfMisfortuneSystem.runSpinner(.6)).whenReleased(() -> wheelOfMisfortuneSystem.runSpinner(0));
    timer.reset();

    // Lowers the Intake Arm
    a1.whileHeld(() -> wheelOfMisfortuneSystem.retractArm(.25, .1));
    a1.whenReleased(() -> wheelOfMisfortuneSystem.extendArm(0));
  }
  
  public void controllerB_configureButtonBindings() {
    // Initializes buttons for second XboxController
    a2 = new JoystickButton(controllerB, 1);
    x2 = new JoystickButton(controllerB, 3);
    y2 = new JoystickButton(controllerB, 4);  

    // Increases Shooter Angle
    y2.whileHeld(() -> shooterSystem.setShooterPitch(.2));
    y2.whenReleased(() -> shooterSystem.setShooterPitch(0));
    
    // Reverses the Index
    x2.whileHeld(() -> intakeSystem.reverseIndex(.6));
    x2.whenReleased(() -> intakeSystem.reverseIndex(0));

    // Decreases Shooter Angle
    a2.whileHeld(() -> shooterSystem.setShooterPitch(-.2));
    a2.whenReleased(() -> shooterSystem.setShooterPitch(0));
  }
  
  public void periodic() {
    // If the left bumper of controllerA is pressed, runs the intake and index
    if(controllerA.getTriggerAxis(Hand.kLeft) == 1) {
      intakeSystem.runIntake(1);
    } else if(controllerA.getTriggerAxis(Hand.kRight) == 1){
      intakeSystem.runIndex(.65);
    } else if(controllerA.getBumper(Hand.kLeft) == true) {
      intakeSystem.reverseIntake(1);
    } else if(controllerA.getBumper(Hand.kRight) == true) {
      intakeSystem.reverseIndex(.65);
    }
    // If the right bumper of controllerB is pressed, runs the index
    else if(controllerB.getBumper(Hand.kRight) == true) {
      intakeSystem.runIndex(.4);
    }
    // If the left trigger of controllerB is pressed, begins scheduled shoot procedure
    if(controllerB.getTriggerAxis(Hand.kLeft) == 1) {
      // If shooting is false, runs the shooter and starts the timer
      if(!shooting) {
        shooterSystem.runShooter(.8);
        timer.reset();
        timer.start();
        shooting = true;
      }
      // If the shooter has spun up for 1 second, runs the index
      else if(timer.get()>=1) {
        intakeSystem.runIndex(.5);
      }
    }
    // Ceases the Shooter, Index, Intake, and Timer if the left trigger and right bumper of controllerB are not being pressed
    else {
      shooterSystem.runShooter(0);
      shooterSystem.runKicker(0);
      intakeSystem.runIndex(0);
      intakeSystem.runIntake(0);
      timer.stop();
      shooting = false;
    }
  
    // Extends the climber if the Up Arrow of the DPad is being pressed on controllerB
    if(controllerB.getPOV()==360 || controllerB.getPOV()==0) {
      climberSystem.extendClimber(.6);
    }
    // Retracts the climber if the Down Arrow of the DPad is being pressed on controllerB
    else if(controllerB.getPOV()==180) {
      climberSystem.retractClimber(.2);
    }
    // Ceases the extension or retraction of the Intake Arm if the Up or Down Arrows of the DPad are not being pressed on controllerB
    else {
      climberSystem.retractClimber(0);
    }

    // Runs the shooter if the left bumper of controllerB is pressed
    if(controllerB.getBumper(Hand.kLeft) == true) {
      shooterSystem.runShooter(.8);
    }
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonomous;
  }


  //Getters
}
