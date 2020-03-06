/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Spinner extends SubsystemBase {  
  private WPI_VictorSPX spinner;
  private Encoder spin_e;

  public Spinner() {
    spinner = new WPI_VictorSPX(21);
    spin_e = new Encoder(2,3,false,EncodingType.k1X);
    spin_e.setDistancePerPulse(wheelCircumference(20));
    spin_e.setSamplesToAverage(5);
  }

  public void resetSpinEncoder() {
    spin_e.reset();
  }

  public void runSpinner(double speed) {
    spinner.set(speed);
  }

  public void reverseSpinner(double speed) {
    spinner.set(-speed);
  }

  public double inchesToMeters(double inches) {
    return inches / 39.37;
  }

  public double wheelCircumference(double diameter) {
    return Math.PI * inchesToMeters(diameter);
  }

  public double getEncoderDistance() {
    return spin_e.getDistance();
  }
}