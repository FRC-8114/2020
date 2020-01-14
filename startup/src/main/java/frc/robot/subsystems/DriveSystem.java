/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.XboxController;

public class DriveSystem extends SubsystemBase {
  private XboxController controller;
  
  private PWMVictorSPX backLeft, frontLeft, backRight, frontRight;
  private SpeedControllerGroup left, right;
  private DifferentialDrive driveTrain;

  public DriveSystem(XboxController controller) {
    this.controller = controller;

    backLeft = new PWMVictorSPX(0);
    frontLeft = new PWMVictorSPX(1);
    backRight = new PWMVictorSPX(2);
    frontRight = new PWMVictorSPX(3);

    left = new SpeedControllerGroup(backLeft, frontLeft);
    right = new SpeedControllerGroup(backRight, frontRight);

    driveTrain = new DifferentialDrive(left, right);
  }

  @Override
  public void periodic() {
    driveTrain.tankDrive(-Math.floor(controller.getY(Hand.kLeft)*100)/100, -Math.floor(controller.getY(Hand.kRight)*100)/100, false);
  }
}
