package frc.robot.subsystems;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class NetworkSystem extends SubsystemBase {
    private RobotContainer container;
    public NetworkTable table;
    public NetworkTableInstance inst;
    private ShuffleboardTab tab;

    public NetworkTableEntry driveSpeed, shooterSpeed, indexShootingSpeed, indexNormalSpeed, intakeSpeed;

    public NetworkSystem() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("datatable");
        tab = Shuffleboard.getTab("motor-speeds");

        driveSpeed = tab.add("speed-modifier", 1).getEntry();
        shooterSpeed = tab.add("shooter-speed", 0.8).getEntry();
        indexShootingSpeed = tab.add("index-shooting-speed", .4).getEntry();
        indexNormalSpeed = tab.add("index-normal-speed", .7).getEntry();
        intakeSpeed = tab.add("intake-speed", .9).getEntry();

        inst.startServer();
        inst.startClientTeam(8114);
    }

    public void updateValues() {
        container.driveSystem.speedModifier = shooterSpeed.getDouble(1);
        container.shootingSpeed = shooterSpeed.getDouble(.8);
        container.indexShootingSpeed = indexShootingSpeed.getDouble(.4);
        container.indexNormalSpeed = indexNormalSpeed.getDouble(.7);
        container.intakeSpeed = intakeSpeed.getDouble(.9);
    }

    public NetworkTable getTable() {
        return table;
    }
}