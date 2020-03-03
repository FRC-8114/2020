/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Intake extends SubsystemBase {  
  private WPI_VictorSPX intake;

  public Intake() {
    intake = new WPI_VictorSPX(15);
  }

  public void runIntake(double speed) {
    intake.set(speed);
    intake.set(speed);
  }

  public void reverseIntake(double speed) {
    intake.set(-speed);
    intake.set(-speed);
  }
}