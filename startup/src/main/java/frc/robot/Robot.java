/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import edu.wpi.cscore.UsbCamera;

import frc.robot.Auto.AutoSegment;

public class Robot extends TimedRobot {
  //--Objects for motors and motor grouping--\\
  private SpeedController leftBack, leftFront, rightBack, rightFront,
                          launcherConveyer, launcherTop, launcherBottom, wheelSpinner;
  private SpeedControllerGroup left, right;
  private DifferentialDrive motorDrive;

  //--Objects for inputs--\\
  private UsbCamera mainCamera;

  //--Objects for user inputs
  private XboxController controller;

  //--Objects for autonomous
  private AutoSegment autoSegment;

  @Override
  public void robotInit() {
    //--Initilization of motors
    /*
     * Drive train Motors
     * 
     * 0 = Left Back
     * 1 = Left Front
     * 2 = Right Back
     * 3 = Right Front
     * 4 = Conveyer Belt
     * 5 = Top Launch Wheel
     * 6 = Bottom Launch Wheel
     * 7 = Wheel Motor
     * 8 = Arm Rotation Motor (maybe )
    */
    
    //-Motors for drive train
    leftBack = new PWMVictorSPX(0);
    leftFront = new PWMVictorSPX(1);
    rightBack = new PWMVictorSPX(2);
    rightFront = new PWMVictorSPX(3);
    //-Motors for ball launcher
    launcherConveyer = new PWMVictorSPX(4);
    launcherTop = new PWMVictorSPX(5);
    launcherBottom = new PWMVictorSPX(6);
    //-Motor for wheel spinner
    wheelSpinner = new PWMVictorSPX(7);
    /*              0
     *   o   o   o
     *              0
     */

    // Speed controllers for controling halves of the drive train motors as a group
    left = new SpeedControllerGroup(leftBack, leftFront);
    right = new SpeedControllerGroup(rightBack, rightFront);

    //Initiates the motor and the controller objects
    motorDrive = new DifferentialDrive(left, right);

    //--Initilization of input devices
    //-Buttons
    controller = new XboxController(0); //joystick ID zero, can change
    //-Camera
    mainCamera = new UsbCamera("FrontCamera", 1); //Camera
    /**
     * PCODE for button init
     * HashMap<Button, String> buttons = new HashMap<Button, String>(9);
     * buttons.put(autoButton(lolnumber), "autoButton");
     * ...
     * buttons.get("autoButton").state();
     */
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
    motorDrive.tankDrive((-Math.floor(controller.getY(Hand.kLeft)*100)/100)*.75, (-Math.floor(controller.getY(Hand.kRight)*100)/100)*.75);
  }

  public void autonomousInit() {
    autoSegment = new AutoSegment(motorDrive);
    autoSegment.moveOffLine();
  }

  public void autonomousPeriodic() {
    motorDrive.tankDrive(0,0);
  }
}
