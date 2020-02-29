package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class ArmLower extends CommandBase {
    private WheelOfMisfortuneSystem arm;
    private double speed;

    public ArmLower(WheelOfMisfortuneSystem arm, double speed) {
        addRequirements(arm);
        this.arm = arm;
        this.speed = speed;
    }

    public void execute() {
        arm.extendArm(-speed);
    }

    public boolean isFinished() {
        return false;
    }
}