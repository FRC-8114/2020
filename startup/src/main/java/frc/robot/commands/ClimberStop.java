package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberStop extends CommandBase {

    private Climber climber;

    public ClimberStop(Climber climber) {
        this.climber = climber;
    }

    public void execute() {
        climber.extend(0);
    }

    public boolean isFinished() {
        return true;
    }
}