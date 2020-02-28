package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class ClimberArmStop extends CommandBase {
    private WheelOfMisfortuneSystem climber;

    public ClimberArmStop(WheelOfMisfortuneSystem climber) {
        this.climber = climber;
    }

    public void execute() {
        climber.runSpinner(0);
    }

    public boolean isFinished() {
        return true;
    }
}