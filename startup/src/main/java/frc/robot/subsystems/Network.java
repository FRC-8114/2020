package frc.robot.subsystems;

import frc.robot;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Network extends SubsystemBase {
    private RobotContainer container;

    private ShuffleboardTab tab;
    private NetworkTableEntry driveMax, shootHigh, shootLow, indexHigh, indexLow, intake, climbUp, climbDown;
    
    public Network() {
        tab = Shuffleboard.getTab("Motor Speeds");

        driveMax = tab.add("Drive Max", 1.0).getEntry();
        shootHigh = tab.add("Shoot High", .8).getEntry();
        shootLow = tab.add("Shoot Low", .3).getEntry();
        indexHigh = tab.add("Index High", .6).getEntry();
        indexLow = tab.add("Index Low", .3).getEntry();
        intake = tab.add("Intake", .1).getEntry();
        climbUp = tab.add("Climb Up", .6).getEntry();
        climbDown = tab.add("Climb Down", .4).getEntry();
    }   


    public void updateValues() {

    }
}