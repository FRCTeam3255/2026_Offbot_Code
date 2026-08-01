// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.RPM;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.units.measure.AngularVelocity;

/** Add your docs here. */
public class ConstFreeSpin {

  public static final double STOP = 0;

  public static final AngularVelocity INTAKE_ROLLERS_WEST_TARGET_VELOCITY = RPM.of(4000);
  public static final AngularVelocity INTAKE_ROLLERS_EAST_TARGET_VELOCITY = RPM.of(4250);
  public static final AngularVelocity EAST_FLYWHEEL_TARGET_VELOCITY = RPM.of(4100);
  public static final AngularVelocity WEST_FLYWHEEL_TARGET_VELOCITY = RPM.of(4000);
  public static final AngularVelocity HOTDOG_ROLLERS_TARGET_VELOCITY = RPM.of(4000);
  public static final AngularVelocity TRANSFER_BELT_TARGET_VELOCITY = RPM.of(4100);
  public static final AngularVelocity AGITATOR_TARGET_VELOCITY = RPM.of(4100);
  public static final AngularVelocity TRANSFER_RAMP_TARGET_VELOCITY = RPM.of(4100);

  public static final TalonFXConfiguration INTAKE_ROLLERS_WEST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration INTAKE_ROLLERS_EAST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration HOTDOG_ROLLERS_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TRANSFER_BELT_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration FLYWHEEL_WEST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration FLYWHEEL_EAST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration AGITATOR_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TRANSFER_RAMP_CONFIGURATION = new TalonFXConfiguration();

  static {
    // Configure TalonFXConfiguration objects here
  }
}
