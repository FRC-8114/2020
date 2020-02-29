package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class ArmLower extends CommandBase {
    private WheelOfMisfortuneSystem arm;
    private double speed;
    private int counter;

    public ArmLower(WheelOfMisfortuneSystem arm, double speed) {
        addRequirements(arm);
        this.arm = arm;
        this.speed = speed;
        counter = 0;
    }

    public void execute() {
        arm.extendArm(-speed);
        counter++;
        System.out.println(counter);
    }

    public void end() {
        arm.extendArm(0);
    }

    public boolean isFinished() {
        if(counter < 20) {
            return false;
        }
        return true;
    }
}