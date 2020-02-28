package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.WheelOfMisfortuneSystem;

public class ClimberArmRaise extends CommandBase {
    private WheelOfMisfortuneSystem climber;

    public ClimberArmRaise(WheelOfMisfortuneSystem climber) {
        this.climber = climber;
    }

    public void execute() {
        climber.runSpinner(.6);
    }

    public boolean isFinished() {
        return true;
    }
}