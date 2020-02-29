package frc.robot.commands;

import frc.robot.subsystems.NetworkSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class UpdateNetworkedValues extends CommandBase {
    NetworkSystem network;

    public UpdateNetworkedValues(NetworkSystem network) {
        this.network = network;
    }

    public void execute() {
        network.updateValues();
    }

    public boolean isFinished() {
        return true;
    }
}