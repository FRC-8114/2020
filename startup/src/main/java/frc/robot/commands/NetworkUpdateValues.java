package frc.robot.commands;

import frc.robot.subsystems.Network;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class NetworkUpdateValues extends CommandBase {
    private Network network;

    public NetworkUpdateValues(Network network) {
        this.network = network;
    }

    public void execute() {
        network.updateValues();
    }

    public boolean isFinished() {
        return true;
    }
}