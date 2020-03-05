/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.XboxController;

public class DriveTrain extends SubsystemBase {
  private XboxController controller;
  
  private WPI_VictorSPX backLeft, frontLeft, backRight, frontRight;
  private SpeedControllerGroup left, right;
  private DifferentialDrive driveTrain;

  private Encoder leftEncoder, rightEncoder;

  public double speedModifier;

  public DriveTrain(XboxController controller) {
    this.controller = controller;

    speedModifier = 1;

    frontLeft = new WPI_VictorSPX(1);
    backLeft = new WPI_VictorSPX(2);
    frontRight = new WPI_VictorSPX(3);
    backRight = new WPI_VictorSPX(4);

    leftEncoder = new Encoder(8, 9);
    rightEncoder = new Encoder(6, 7);
    setEncoderDistances(wheelCircumference(6));

    left = new SpeedControllerGroup(backLeft, frontLeft);
    right = new SpeedControllerGroup(backRight, frontRight);

    driveTrain = new DifferentialDrive(left, right);
  }

  @Override
  public void periodic() {
    driveTrain.tankDrive(-speedModifier*Math.floor(controller.getY(Hand.kLeft)*100)/100, -speedModifier*Math.floor(controller.getY(Hand.kRight)*100)/100, false);
  }

  public void driveForward(double speed) {
    driveTrain.tankDrive(speed, speed, false);
  }

  public void driveBackward(double speed) {
      driveTrain.tankDrive(-speed, -speed, false);
  }

  public void turnLeft(double left, double right) {
      driveTrain.tankDrive(-left, right, false);
  }

  public void turnRight(double left, double right) {
    driveTrain.tankDrive(left, -right, false);
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public void setEncoderDistances(double distance) {
    leftEncoder.setDistancePerPulse(distance);
    rightEncoder.setDistancePerPulse(distance);
  }

  public double inchesToMeters(double inches) {
    return inches / 39.37;
  }

  public double wheelCircumference(double diameter) {
    return Math.PI * inchesToMeters(diameter);
  }

  public double getRightEncoderDistance() {
    return rightEncoder.getDistance();
  }

  public double getLeftEncoderDistance() {
    return leftEncoder.getDistance();
  }
}