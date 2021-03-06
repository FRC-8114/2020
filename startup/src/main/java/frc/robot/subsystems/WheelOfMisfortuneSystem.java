package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelOfMisfortuneSystem extends SubsystemBase{
    private WPI_VictorSPX armLeft, armRight, spinner;

    public WheelOfMisfortuneSystem() {
        armLeft = new WPI_VictorSPX(19);
        armRight = new WPI_VictorSPX(20);
        spinner = new WPI_VictorSPX(21);
    }


    public void runSpinner(double speed) {
        spinner.set(speed);
    }


    public void extendArm(double speed) {
        armLeft.set(speed);
        armRight.set(-speed);
    }

    public void retractArm(double speed) {
        armLeft.set(-speed);
        armRight.set(speed);
    }
}