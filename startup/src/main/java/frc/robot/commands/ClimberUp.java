package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberUp extends CommandBase {

    private Climber climber;
    private double speed;

    public ClimberUp(Climber climber, double speed) {
        this.climber = climber;
        this.speed = speed;
    }

    public void initialize() {
        climber.extend(speed);
    }
}