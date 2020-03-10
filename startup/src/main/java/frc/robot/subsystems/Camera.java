/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.links.SPILink;

public class Camera extends SubsystemBase {  

  private static Pixy2 pixy;
  private final int blockSignature = 1;
  private Block largestBlock;

  public Camera() {
    pixy = Pixy2.createInstance(new SPILink());
    pixy.init();
  }

  public void lampOn() {
    pixy.setLamp((byte) 1, (byte) 1);
  }

  public void LEDOn(int a, int b, int c) {
    pixy.setLED(a, b, c);
  }

  public void lampOff() {
    pixy.setLamp((byte) 0, (byte) 0);
  }

  public void LEDOff() {
    pixy.setLED(0,0,0);
  }

  public Block getBiggestBlock() {
    int blockCount = pixy.getCCC().getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
    if (blockCount <= 0) {
        return null;
    }
    ArrayList<Block> blocks = pixy.getCCC().getBlockCache();
    largestBlock = null;
    for (Block block : blocks) {
        if (block.getSignature() == blockSignature) {
            if (largestBlock == null)
                largestBlock = block;
            else if (block.getWidth() > largestBlock.getWidth())
                largestBlock = block;
        }
    }
    return largestBlock;
  }

  public double getLargestBlockX() {
    return largestBlock.getX();
  }

  public double getLargestBlockY() {
    return largestBlock.getY();
  }
}