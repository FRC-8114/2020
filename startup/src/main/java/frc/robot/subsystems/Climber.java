/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Climber extends SubsystemBase {  
  private WPI_VictorSPX climberLeft, climberRight;

  public Climber() {
    climberLeft = new WPI_VictorSPX(23);
    climberRight = new WPI_VictorSPX(24);
  }

  public void extend(double speed) {
    climberLeft.set(speed);
    climberRight.set(speed);
  }

  public void retract(double speed) {
    climberLeft.set(-speed);
    climberRight.set(-speed);
  }

  public void hold(double speed) {
    climberLeft.set(speed);
    climberRight.set(speed);
  }
}