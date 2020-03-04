package frc.robot.commands;

import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ClimberDown extends CommandBase {

    private Climber climber;
    private double speed;

    public ClimberDown(Climber climber, double speed) {
        this.climber = climber;
        this.speed = speed;
    }

    public void initialize() {
        climber.retract(speed);
    }
}