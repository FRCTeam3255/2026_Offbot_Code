// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.RPM;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.units.measure.AngularVelocity;

/** Add your docs here. */
public class ConstFreeSpin {

  // REVERSING FREESPIN MECHANISM VELOCITIES
  public static final double STOP = 0;
  public static final double REVERSING_FLYWHEEL_PERCENT_OUTPUT = -1;
  public static final double REVERSING_TRANSFER_BELT_PERCENT_OUTPUT = -1;
  public static final double REVERSING_TRANSFER_RAMP_PERCENT_OUTPUT = -1;
  public static final double REVERSING_AGITATOR_PERCENT_OUTPUT = -1;
  public static final double REVERSING_HOTDOG_ROLLERS_PERCENT_OUTPUT = -1;
  public static final double REVERSING_INTAKE_PERCENT_OUTPUT = -1;

  public static final double TRANSFER_BELT_PERCENT_OUTPUT = 1;
  public static final double TRANSFER_RAMP_PERCENT_OUTPUT = 1;
  public static final double AGITATOR_PERCENT_OUTPUT = 1;
  public static final double HOTDOG_ROLLERS_PERCENT_OUTPUT = 1;
  public static final double INTAKE_ROLLER_PERCENT_OUTPUT = 1;

  // FLYWHEEL TOLERANCE
  public static final AngularVelocity FLYWHEEL_TOLERANCE = RPM.of(100);

  // TRANSFER VELOCITIES
  public static final AngularVelocity TRANSFER_BELT_VELOCITY = RPM.of(6000);// TODO: replace with actual values
  public static final AngularVelocity TRANSFER_RAMP_VELOCITY = RPM.of(6000);// TODO: replace with actual values

  // SERIALIZER VELOCITIES
  public static final AngularVelocity AGITATOR_VELOCITY = RPM.of(6000);// TODO: replace with actual values
  public static final AngularVelocity HOTDOG_ROLLERS_VELOCITY = RPM.of(6000);// TODO: replace with actual values

  // FLYWHEEL VELOCITIES
  public static final AngularVelocity PREP_D_SIDE_FLYWHEEL = RPM.of(4100);// TODO: find actual value
  public static final AngularVelocity PREP_O_SIDE_FLYWHEEL = RPM.of(4250);// TODO: find actual value
  public static final AngularVelocity PREP_HUB_FLYWHEEL = RPM.of(4000);// TODO: find actual value
  public static final AngularVelocity PREP_NEUTRAL_TO_ALLIANCE_FLYWHEEL = RPM.of(3700);// TODO: find actual value
  public static final AngularVelocity PREP_OPPOSING_TO_ALLIANCE_FLYWHEEL = RPM.of(6400);// TODO: find actual value
  public static final AngularVelocity PREP_TOWER_FLYWHEEL = RPM.of(4000);// TODO: find actual value
  public static final AngularVelocity PREP_TRENCH_FLYWHEEL = RPM.of(4000);// TODO:find actual value

  // FREESPIN MECHANISM CONFIGURATIONS
  public static final TalonFXConfiguration FLYWHEEL_WEST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration FLYWHEEL_EAST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TRANSFER_RAMP_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TRANSFER_BELT_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration AGITATOR_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration HOTDOG_ROLLERS_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration INTAKE_ROLLERS_WEST_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration INTAKE_ROLLERS_EAST_CONFIGURATION = new TalonFXConfiguration();

  public final static InterpolatingDoubleTreeMap flywheelSpeedMap = new InterpolatingDoubleTreeMap();
  static {
    // TODO: tune
    flywheelSpeedMap.put(Inches.of(190).in(Inches), RPM.of(4200).in(RPM));
    flywheelSpeedMap.put(Inches.of(180).in(Inches), RPM.of(4125).in(RPM));
    flywheelSpeedMap.put(Inches.of(165.1).in(Inches), RPM.of(4100).in(RPM));
    flywheelSpeedMap.put(Inches.of(165).in(Inches), RPM.of(4000).in(RPM));
    flywheelSpeedMap.put(Inches.of(160).in(Inches), RPM.of(3800).in(RPM));
    flywheelSpeedMap.put(Inches.of(150).in(Inches), RPM.of(3700).in(RPM));
    flywheelSpeedMap.put(Inches.of(140).in(Inches), RPM.of(3600).in(RPM));
    flywheelSpeedMap.put(Inches.of(120).in(Inches), RPM.of(3400).in(RPM));
    flywheelSpeedMap.put(Inches.of(0).in(Inches), RPM.of(3400).in(RPM));
    INTAKE_ROLLERS_EAST_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive; // Configure
                                                                                                      // TalonFXConfiguration
                                                                                                      // objects here
    INTAKE_ROLLERS_WEST_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    FLYWHEEL_EAST_CONFIGURATION.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    FLYWHEEL_WEST_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    TRANSFER_BELT_CONFIGURATION.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    TRANSFER_RAMP_CONFIGURATION.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    HOTDOG_ROLLERS_CONFIGURATION.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    AGITATOR_CONFIGURATION.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
  }
}
