package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class ArmStop extends CommandBase {
    private WheelOfMisfortuneSystem arm;

    public ArmStop(WheelOfMisfortuneSystem arm) {
        addRequirements(arm);
        this.arm = arm;
    }

    public void execute() {
        arm.extendArm(0);
    }

    public boolean isFinished() {
        return false;
    }
}