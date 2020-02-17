/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.XboxController;

public class DriveSystem extends SubsystemBase {
  private XboxController controllerA;
  
  private WPI_VictorSPX backLeft, frontLeft, backRight, frontRight;
  private SpeedControllerGroup left, right;
  private DifferentialDrive driveTrain;

  public DriveSystem(XboxController controllerA) {
    this.controllerA = controllerA;

    frontLeft = new WPI_VictorSPX(1);
    backLeft = new WPI_VictorSPX(2);
    frontRight = new WPI_VictorSPX(3);
    backRight = new WPI_VictorSPX(4);

    left = new SpeedControllerGroup(backLeft, frontLeft);
    right = new SpeedControllerGroup(backRight, frontRight);

    driveTrain = new DifferentialDrive(left, right);
  }

  @Override
  public void periodic() {
    driveTrain.tankDrive(-0.75*Math.floor(controllerA.getY(Hand.kLeft)*100)/100, -0.75*Math.floor(controllerA.getY(Hand.kRight)*100)/100, false);
  }

  public void drive(double left, double right) {
    driveTrain.tankDrive(left, right, false);
  }
}
