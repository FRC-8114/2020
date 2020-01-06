/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private SpeedController zero, one, two, three;
  private SpeedControllerGroup left, right;
  private Joystick stick;
  private DifferentialDrive MotorDrive;
  //private Joystick m_rightStick;

  @Override
  public void robotInit() {
    zero = new PWMVictorSPX(0);
    one = new PWMVictorSPX(1);
    two = new PWMVictorSPX(2);
    three = new PWMVictorSPX(3);

    left = new SpeedControllerGroup(zero, one);
    right = new SpeedControllerGroup(two, three);

    MotorDrive = new DifferentialDrive(left, right);
    stick = new Joystick(0);
    //m_rightStick = new Joystick(1);
  }

  @Override
  public void teleopPeriodic() {
    MotorDrive.arcadeDrive(-stick.getY(), -stick.getX());
  }
}
