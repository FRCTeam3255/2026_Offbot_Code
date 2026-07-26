// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

/** Add your docs here. */
public class ConstFreeSpin {

  public static final double STOP = 0;
  public static final double INTAKE_ROLLERS_WEST_SPEED = 1.0;
  public static final double INTAKE_ROLLERS_EAST_SPEED = 1.0;
  public static final double EAST_FLYWHEEL_SPEED = 1.0;
  public static final double WEST_FLYWHEEL_SPEED = 1.0;
  public static final double HOTDOG_ROLLERS_SPEED = 1.0;
  public static final double TRANSFER_BELT_SPEED = 1.0;
  public static final double AGITATOR__SPEED = 1.0;
  public static final double TRANSFER_RAMP_SPEED = 1.0;

  public static final TalonFXConfiguration INTAKE_ROLLERS_WEST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration INTAKE_ROLLERS_EAST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration HOTDOG_ROLLERS_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TRANSFER_BELT_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration WEST_FLYWHEEL_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration EAST_FLYWHEEL_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration AGITATOR_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TRANSFER_RAMP_CONFIGURATION = new TalonFXConfiguration();

  static {
    // Configure TalonFXConfiguration objects here
  }
}
