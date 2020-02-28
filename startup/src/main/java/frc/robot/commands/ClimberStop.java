package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSystem;

public class ClimberStop extends CommandBase {
    private ClimberSystem climber;

    public ClimberStop(ClimberSystem climber) {
        this.climber = climber;
    }

    public void execute() {
        climber.extendClimber(0);
    }

    public boolean isFinished() {
        return true;
    }
}