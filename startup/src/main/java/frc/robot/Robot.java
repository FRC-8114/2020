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

public class Robot extends TimedRobot {
  private SpeedController zero, one, two, three;
  private SpeedControllerGroup left, right;
  private Joystick stick;
  private DifferentialDrive motorDrive;


  @Override
  public void robotInit() {
    /*
     * Drive train Motors
     * 
     * 0 = Left Back
     * 1 = Left Front
     * 2 = Right Back
     * 3 = Right Front
    */
    
    zero = new PWMVictorSPX(0);
    one = new PWMVictorSPX(1);
    two = new PWMVictorSPX(2);
    three = new PWMVictorSPX(3);

    //Speed controllers for controling halves of the drive train motors as a group
    left = new SpeedControllerGroup(zero, one);
    right = new SpeedControllerGroup(two, three);

    //Initiates the motor and the joystick objects
    MotorDrive = new DifferentialDrive(left, right);
    stick = new Joystick(0); //joystick ID zero, can change
  }

  @Override
  public void teleopPeriodic() {
    /**
     * The stick is mapped like this:
     * Y AXIS IS UP AND DOWN
     * X AXIS IS LEFT AND RIGHT
     *     -1.0
     * -1.0     1.0
     *      1.0
     * Invert just the getY() to get movement right
     * Joystick has drift, use stepping by 100 steps ie
     *    1.0 ~ 1.0
     *    0.938 ~ 0.93
     *    0.006 ~ 0.0
     */

      motorDrive.arcadeDrive(-Math.floor(stick.getY()*100)/100, Math.floor(stick.getX()*100)/100);
  }
}
