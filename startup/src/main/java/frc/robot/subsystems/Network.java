package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.*;

public class Network extends SubsystemBase {
    private ShuffleboardTab buttons;
    
    public Network(Shooter shooter, Index index) {
        /* SmartDashboard Buttons for Shooter Control */
        SmartDashboard.putData("Shooter run high", new ShooterForward(shooter, .8));
        SmartDashboard.putData("Shooter run low", new ShooterForward(shooter, .4));
        SmartDashboard.putData("Shooter stop", new ShooterStop(shooter));

        /* SmartDashboard Buttons for Index Control */
        SmartDashboard.putData("Run index", new IndexForward(index, .3));
        SmartDashboard.putData("Index stop", new IndexStop(index));

        /* SmartDashboard Buttons for  */
    }   
}