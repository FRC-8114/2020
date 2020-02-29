package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSystem;

public class ClimberLower extends CommandBase {
    private ClimberSystem climber;

    public ClimberLower(ClimberSystem climber) {
        this.climber = climber;
    }

    public void execute() {
        climber.retractClimber(1);
    }

    public boolean isFinished() {
        return true;
    }
}