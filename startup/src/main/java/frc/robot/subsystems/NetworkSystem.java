package frc.robot.subsystems;

import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkSystem extends SubsystemBase {
    public NetworkTable table;
    public NetworkTableInstance inst;

    public NetworkSystem() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("");
    }

    public NetworkTable getTable() {
        return table;
    }
}