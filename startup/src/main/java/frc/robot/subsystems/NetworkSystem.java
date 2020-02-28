package frc.robot.subsystems;

import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class NetworkSystem extends SubsystemBase {
    private RobotContainer container;
    public NetworkTable table;
    public NetworkTableInstance inst;
    public NetworkTableEntry driveSpeed;

    public NetworkSystem() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("");

        driveSpeed = table.getEntry("speed-modifier");

        inst.startClient("");
    }

    public void UpdateValues() {
        container.driveSystem.speedModifier = driveSpeed.getDouble(1);
    }

    public NetworkTable getTable() {
        return table;
    }
}