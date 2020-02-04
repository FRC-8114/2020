package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.CAN; 

public class PowerSystem extends SubsystemBase {
 private PowerDistributionPanel pdp;

     public PowerSystem(PowerDistributionPanel pdp) {
         this.pdp = pdp;
     }

     public void periodic() {
        System.out.println("PDP voltage: "+ pdp.getVoltage());
        System.out.println("PDP temperature: "+ pdp.getTemperature());
        System.out.println("PDP total energy: "+ pdp.getTotalEnergy());
        System.out.println("PDP total current: "+ pdp.getTotalCurrent());
        System.out.println("PDP total power" + pdp.getTotalPower());
    }
}