package frc.robot.subsystems;

import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class NetworkSystem extends SubsystemBase {
    private RobotContainer container;
    public NetworkTable table;
    public NetworkTableInstance inst;
    public NetworkTableEntry driveSpeed, shooterSpeed, indexShootingSpeed, indexNormalSpeed, intakeSpeed;

    public NetworkSystem() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("");

        driveSpeed = table.getEntry("speed-modifier");
        shooterSpeed = table.getEntry("shooter-speed");
        indexShootingSpeed = table.getEntry("index-shoot-speed");
        indexNormalSpeed = table.getEntry("index-normal-speed");
        intakeSpeed = table.getEntry("intake-speed");

        inst.startClient("");
    }

    public void UpdateValues() {
        container.driveSystem.speedModifier = driveSpeed.getDouble(1);
        container.shootingSpeed = shooterSpeed.getDouble(.8);
        container.indexShootingSpeed = indexShootingSpeed.getDouble(.4);
        container.indexNormalSpeed = indexNormalSpeed.getDouble(.7);
        container.intakeSpeed = intakeSpeed.getDouble(.8);
    }

    public NetworkTable getTable() {
        return table;
    }
}