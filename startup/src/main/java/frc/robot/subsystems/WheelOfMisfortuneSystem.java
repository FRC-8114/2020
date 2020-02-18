package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Timer;

public class WheelOfMisfortuneSystem extends SubsystemBase{
    private WPI_VictorSPX armLeft, armRight, spinner;
    private Timer timer;

    public WheelOfMisfortuneSystem() {
        armLeft = new WPI_VictorSPX(19);
        armRight = new WPI_VictorSPX(20);
        spinner = new WPI_VictorSPX(21);
    }


    public void runSpinner(double speed) {
        spinner.set(speed);
    }


    public void extendArm(double speed) {
        armLeft.set(-speed);
        armRight.set(speed);
    }

    public void retractArm(double speed, double delay) {
        timer.start();
        armLeft.set(speed);
        armRight.set(-speed);
        double current = timer.get();
        if(current >= delay && current < 3) {
            if (current > 1) {
                current = 1;
            }
            double newspd = current-delay;
            if(newspd>.4) {
                newspd=.4;
            }
            armLeft.set(-newspd);
            armRight.set(newspd);
        }
    }
}