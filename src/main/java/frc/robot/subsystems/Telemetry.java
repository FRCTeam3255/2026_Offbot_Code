// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Logged
public class Telemetry extends SubsystemBase {
  /** Creates a new telemetry. */
  public Telemetry() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getMatchTime() {
    return DriverStation.getMatchTime();
  }

  public Voltage batteryVoltage() {
    return RobotController.getMeasureBatteryVoltage();
  }

  public boolean isBrownedOut() {
    return RobotController.isBrownedOut();
  }
}
