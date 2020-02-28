/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  public final DriveSystem driveSystem;
  public final CameraSystem cameraSystem;
  public final OdometrySubsystem odometrySystem;
  public final NetworkSystem networkSystem;
  public final Autonomous autonomous;
  //private final PowerSystem powerSystem;
  public final ShooterSystem shooterSystem;
  public final WheelOfMisfortuneSystem wheelOfMisfortuneSystem;
  public final IntakeSystem intakeSystem;
  public final ClimberSystem climberSystem;

  private final XboxController controllerA;
  private static JoystickButton a1, y1, lb1, rb1;
  private static Trigger rt1, lt1;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(XboxController controllerA, XboxController controllerB/*, PowerDistributionPanel pdp*/) {
    // Initializes the various subsystems
    driveSystem = new DriveSystem(controllerA);
    cameraSystem = new CameraSystem();
    odometrySystem = new OdometrySubsystem();
    networkSystem = new NetworkSystem();
    //powerSystem = new PowerSystem(pdp);
    shooterSystem = new ShooterSystem();
    wheelOfMisfortuneSystem = new WheelOfMisfortuneSystem();
    intakeSystem = new IntakeSystem();
    climberSystem = new ClimberSystem();

    // Initializes the various commmands
    autonomous = new Autonomous(driveSystem, odometrySystem, shooterSystem, intakeSystem); // In meters

    // Initializes others
    this.controllerA = controllerA;

    SmartDashboard.putData(shooterSystem);
    SmartDashboard.putData(driveSystem);
    SmartDashboard.putData(intakeSystem);

    SmartDashboard.putData("Shooter on", new ShooterOn(shooterSystem));
    SmartDashboard.putData("Shooter off", new ShooterOff(shooterSystem));

    SmartDashboard.putData("Run index", new IndexOn(intakeSystem)); 
    SmartDashboard.putData("Stop index", new IndexOff(intakeSystem));

    SmartDashboard.putData("Raise climber", new ClimberRaise(climberSystem));
    SmartDashboard.putData("Lower climber", new ClimberLower(climberSystem));
    SmartDashboard.putData("Stop climber", new ClimberStop(climberSystem));
   
    SmartDashboard.putData("Raise climber arm", new ClimberArmRaise(wheelOfMisfortuneSystem));
    SmartDashboard.putData("Lower climber arm", new ClimberArmLower(wheelOfMisfortuneSystem));
    SmartDashboard.putData("Stop climber arm", new ClimberArmStop(wheelOfMisfortuneSystem));
  
    // Configure the button bindings for the two controllers
    controllerA_configureButtonBindings();
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
    y1 = new JoystickButton(controllerA, 4);
    lb1 = new JoystickButton(controllerA, 5);
    rb1 = new JoystickButton(controllerA, 6);
    lt1 = new Trigger(new BooleanSupplier() {
      @Override
      public boolean getAsBoolean() {
        return controllerA.getTriggerAxis(Hand.kLeft) == 1;
      }});
    rt1 = new Trigger(new BooleanSupplier() {
      @Override
      public boolean getAsBoolean() {
        return controllerA.getTriggerAxis(Hand.kRight) == 1;
      }});

    // Runs the intake
    lt1.whenActive(() -> intakeSystem.runIntake(1));
    lt1.whenInactive(() -> intakeSystem.runIntake(0));

    // Runs the index
    rt1.whenActive(() -> intakeSystem.runIndex(.65));
    rt1.whenInactive(() -> intakeSystem.runIndex(0));
    
    // Raises the Intake Arm
    y1.whileHeld(() -> wheelOfMisfortuneSystem.extendArm(.4));
    y1.whenReleased(() -> wheelOfMisfortuneSystem.extendArm(0));

    // Lowers the Intake Arm
    a1.whileHeld(() -> wheelOfMisfortuneSystem.retractArm(.25, .1));
    a1.whenReleased(() -> wheelOfMisfortuneSystem.extendArm(0));

    // Reverses the intake
    lb1.whileHeld(() -> intakeSystem.runIntake(-1));
    lb1.whenReleased(() -> intakeSystem.runIntake(0));

    // Reverses the index
    rb1.whileHeld(() -> intakeSystem.runIndex(-.65));
    rb1.whenReleased(() -> intakeSystem.runIndex(0));
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
