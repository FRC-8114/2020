package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class ArmRaise extends CommandBase {
    private WheelOfMisfortuneSystem arm;

    public ArmRaise(WheelOfMisfortuneSystem arm) {
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute() {
        arm.extendArm(.3);
    }

    public boolean isFinished() {
        return false;
    }
}