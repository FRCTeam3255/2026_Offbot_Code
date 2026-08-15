// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;

/** Add your docs here. */
public class ConstPositional {
  // outputs
  public static final double STOP = 0;

  // CLIMBER DISTANCES
  public static final Distance RETRACT_CLIMBER_DISTANCE = Units.Inches.of(0);
  public static final Distance DEPLOY_CLIMBER_DISTANCE = Units.Inches.of(10);

  // POSITIONAL MECHANISM TOLERANCES
  public static final Distance CLIMBER_TOLERANCE = Units.Inches.of(0.5);
  public static final Angle TURRET_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance
  public static final Angle HOOD_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance
  public static final Distance INTAKE_SLIDE_TOLERANCE = Inches.of(1);// TODO ADD ACTUAL VALUE LATER

  // FREESPIN MECHANISM PID
  public static final int SLOW_INTAKE_SLIDE_PID = 0;
  public static final int FAST_INTAKE_SLIDE_PID = 1;
  public static final int SLOW_CLIMBER_PID = 0;
  public static final int FAST_CLIMBER_PID = 1;
  // INTAKE DISTANCES
  public static final Distance DEPLOY_INTAKE_SLIDE_DISTANCE = Units.Inches.of(20);
  public static final Distance RETRACTING_INTAKE_SLIDE_DISTANCE = Inches.of(0); // TODO ADD ACTUAL VALUES DURING TESTING

  // HOOD AND TURRET ANGLES
  public static final Angle PREP_D_SIDE_HOOD = Degrees.of(18);// TODO: find actual value
  public static final Angle PREP_D_SIDE_TURRET = Degrees.of(-20.78);// TODO: find actual value

  public static final Angle PREP_O_SIDE_HOOD = Degrees.of(12);// TODO: find actual value
  public static final Angle PREP_O_SIDE_TURRET = Degrees.of(40.11);// TODO: find actual value

  public static final Angle PREP_HUB_HOOD = Degrees.of(9.5);// TODO: find actual value
  public static final Angle PREP_HUB_TURRET = Degrees.of(0);// TODO: find actual value

  public static final Angle PREP_NEUTRAL_TO_ALLIANCE_HOOD = Degrees.of(45);// TODO: find actual value
  public static final Angle PREP_NEUTRAL_TO_ALLIANCE_TURRET = Degrees.of(-180);// TODO: find actual value

  public static final Angle PREP_OPPOSING_TO_ALLIANCE_HOOD = Degrees.of(37);// TODO: find actual value
  public static final Angle PREP_OPPOSING_TO_ALLIANCE_TURRET = Degrees.of(-180);// TODO: find actual value

  public static final Angle PREP_TOWER_HOOD = Degrees.of(14);// TODO: find actual value
  public static final Angle PREP_TOWER_TURRET = Degrees.of(0);// TODO: find actual value

  public static final Angle PREP_TRENCH_HOOD = Degrees.of(13);// TODO: find actual value
  public static final Angle PREP_TRENCH_TURRET = Degrees.of(-91.6732);// TODO: find actual value

  public static final Angle HOOD_ANGLE_RETRACT = Degrees.of(0);
  public static final Angle TURRET_ANGLE_HOME = Degrees.of(0);

  // POSITIONAL MECHANISM CONFIGURATIONS
  public static final TalonFXConfiguration INTAKE_SLIDE_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration HOOD_PIVOT_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TURRET_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration CLIMBER_CONFIGURATION = new TalonFXConfiguration();

  static {
    // Configure TalonFXConfiguration objects here
  }

  // each angles
  public static final double[] TURRET_TO_ENCODER_ANGLES = { 0, 40, 80, 120, 160, 200, 240, 280, 320 };
  public static final double TURRET_TO_MOTOR_ANGLE_CALC = 360 * (12 / (58 * 9));
  public static final double[] TURRET_TO_MOTOR_ANGLES = {0, TURRET_TO_MOTOR_ANGLE_CALC, 2 * TURRET_TO_MOTOR_ANGLE_CALC, 3 * TURRET_TO_MOTOR_ANGLE_CALC}
}
