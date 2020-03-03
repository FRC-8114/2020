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
        armLeft.setInverted(true);
    }


    public void runSpinner(double speed) {
        spinner.set(speed);
    }


    public void extendArm(double speed) {
        armLeft.set(speed);
        armRight.set(speed);
    }

    public void retractArm(double speed, double delay) {
        // Begins the count of the timer
        timer.start();
        // Activates motors (going downwards)
        armLeft.set(-speed);
        armRight.set(-speed);
        // Gets current time
        double current = timer.get();
        // If the current time is more than or equal to the given delay and the current time is less than a given number of seconds, activates motors
        if(current >= delay && current < 3) {
            // If current is more than 1 second, current time equals 1
            if (current > 1)
                current = 1;
            // The new speed equals the current time - the delay
            double newspd = current-delay;
            // If the new speed is more than a quarter of a second, the new speed is equivalent to .25
            if(newspd>.25) {
                newspd=.25;
            }
            // Activates motors (going upwards)
            armLeft.set(newspd);
            armRight.set(newspd);
        }

        if (current > 4) {
            timer.stop();
            timer.reset();
        }
    }
}