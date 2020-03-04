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
  private final DriveTrain drive;
  private final Shooter shooter;
  
  private final XboxController controller;
  private JoystickButton lb, rb;
  private Trigger lt, rt;
  
  public RobotContainer(XboxController controller) {
    this.controller = controller;
    intake = new Intake();
    index = new Index();
    drive = new DriveTrain(controller);
    shooter = new Shooter();

    configureButtonBindings();
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
    lt.whenActive(new IntakeForward(intake, .8));
    lt.whenInactive(new IntakeStop(intake));

    // Runs the index
    rt.whenActive(new IndexForward(index, .6));
    rt.whenInactive(new IndexStop(index));

    // Reverses the intake
    lb.whileHeld(new IntakeBackward(intake, .8));
    lb.whenReleased(new IntakeStop(intake));

    // Reverses the index
    rb.whileHeld(new IndexBackward(index, .8));
    rb.whenReleased(new IndexStop(index));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new ShootAndMove(drive, shooter, intake, index);
  }


  //Getters
}