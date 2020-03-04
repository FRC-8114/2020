/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Shooter extends SubsystemBase {  
  private WPI_VictorSPX left_shooter, right_shooter;

  public Shooter() {
    left_shooter = new WPI_VictorSPX(10);
    right_shooter = new WPI_VictorSPX(11);
  }

  public void runShooter(double speed) {
    left_shooter.set(speed);
    right_shooter.set(speed);
  }

  public void reverseShooter(double speed) {
    left_shooter.set(-speed);
    right_shooter.set(-speed);
  }
}