package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberDown extends CommandBase {

    private Climber climber;

    public ClimberDown(Climber climber) {
        this.climber = climber;
    }

    public void initialize(double speed) {
        climber.retract(speed);
    }
}