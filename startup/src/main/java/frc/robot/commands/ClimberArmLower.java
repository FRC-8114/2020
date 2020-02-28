package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class ClimberArmLower extends CommandBase {
    private WheelOfMisfortuneSystem climber;

    public ClimberArmLower(WheelOfMisfortuneSystem climber) {
        this.climber = climber;
    }

    public void execute() {
        climber.runSpinner(-.15);
    }

    public boolean isFinished() {
        return true;
    }
}