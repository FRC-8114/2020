package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberUp extends CommandBase {

    private Climber climber;

    public ClimberUp(Climber climber) {
        this.climber = climber;
    }

    public void initialize(double speed) {
        climber.extend(speed);
    }
}