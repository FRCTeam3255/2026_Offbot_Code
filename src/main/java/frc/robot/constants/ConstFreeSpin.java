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
  public static final double INTAKE_ROLLER_PERCENT_OUTPUT = 1;
  public static final double REVERSE_INTAKE_PERCENT_OUTPUT = -1;
  public static final double REVERSE_HOTDOG_ROLLERS_PERCENT_OUTPUT = -1;
  public static final double REVERSE_AGITATOR_PERCENT_OUTPUT = -1;
  public static final AngularVelocity FLYWHEEL_TOLERANCE = RPM.of(100);

  public static final AngularVelocity TRANSFER_BELT_PERCENT_VELOCITY = RPM.of(1);
  public static final AngularVelocity TRANSFER_RAMP_PERCENT_VELOCITY = RPM.of(1);
  public static final AngularVelocity HOTDOG_ROLLERS_PERCENT_VELOCITY = RPM.of(1);
  public static final AngularVelocity AGITATOR_PERCENT_VELOCITY = RPM.of(1);
  public static final AngularVelocity PREP_D_SIDE_FLYWHEEL = RPM.of(100);// TODO: find actual value
  public static final AngularVelocity PREP_O_SIDE_FLYWHEEL = RPM.of(100);// TODO: find actual value
  public static final AngularVelocity PREP_HUB_FLYWHEEL = RPM.of(100);// TODO: find actual value
  public static final AngularVelocity PREP_NEUTRAL_TO_ALLIANCE_FLYWHEEL = RPM.of(100);// TODO: find actual value
  public static final AngularVelocity PREP_OPPOSING_TO_ALLIANCE_FLYWHEEL = RPM.of(100);// TODO: find actual value
  public static final AngularVelocity PREP_TOWER_FLYWHEEL = RPM.of(100);// TODO: find actual value
  public static final AngularVelocity PREP_TRENCH_FLYWHEEL = RPM.of(100);// TODO:find actual value

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
