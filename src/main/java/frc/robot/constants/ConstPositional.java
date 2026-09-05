// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Seconds;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;

/** Add your docs here. */
public class ConstPositional {
  // outputs
  public static final double STOP = 0;

  public final static InterpolatingDoubleTreeMap hoodAngleMap = new InterpolatingDoubleTreeMap();
  public final static InterpolatingDoubleTreeMap timeOfFlightMap = new InterpolatingDoubleTreeMap();

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
  public static final Angle MAX_TURRET_ANGLE = Degrees.of(185);
  public static final Angle MIN_TURRET_ANGLE = Degrees.of(-185);

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
    // TODO: Tune
    // ---- Hood Angle Map ---- //
    // hoodAngleMap.put(Inches.of(0).in(Inches), Degrees.of(3).in(Degrees));
    hoodAngleMap.put(Inches.of(200).in(Inches), Degrees.of(29).in(Degrees));
    // hoodAngleMap.put(Inches.of(190).in(Inches), Degrees.of(29).in(Degrees));
    hoodAngleMap.put(Inches.of(180).in(Inches), Degrees.of(24).in(Degrees));
    // hoodAngleMap.put(Inches.of(170).in(Inches), Degrees.of(24).in(Degrees));
    // hoodAngleMap.put(Inches.of(160).in(Inches), Degrees.of(24).in(Degrees));
    // hoodAngleMap.put(Inches.of(150).in(Inches), Degrees.of(24).in(Degrees));
    hoodAngleMap.put(Inches.of(140).in(Inches), Degrees.of(24).in(Degrees)); // good
    // hoodAngleMap.put(Inches.of(130).in(Inches), Degrees.of(16).in(Degrees));
    hoodAngleMap.put(Inches.of(120).in(Inches), Degrees.of(22).in(Degrees)); // good
    // hoodAngleMap.put(Inches.of(110).in(Inches), Degrees.of(13).in(Degrees));
    hoodAngleMap.put(Inches.of(100).in(Inches), Degrees.of(16).in(Degrees)); // little too far, made most
    // hoodAngleMap.put(Inches.of(90).in(Inches), Degrees.of(9).in(Degrees));
    hoodAngleMap.put(Inches.of(80).in(Inches), Degrees.of(13.25).in(Degrees)); //
    // hoodAngleMap.put(Inches.of(70).in(Inches), Degrees.of(6).in(Degrees));
    // hoodAngleMap.put(Inches.of(60).in(Inches), Degrees.of(6).in(Degrees));
    hoodAngleMap.put(Inches.of(50).in(Inches), Degrees.of(1).in(Degrees)); //
    // ---- Time Of Flight Map ---- //
    timeOfFlightMap.put(Inches.of(60).in(Inches), Seconds.of(0).in(Seconds));
    timeOfFlightMap.put(Inches.of(50).in(Inches), Seconds.of(1).in(Seconds));

    // Configure TalonFXConfiguration objects here
    INTAKE_SLIDE_CONFIGURATION.Feedback.SensorToMechanismRatio = 1.0 / ((10.0 / 30.0) * (1.0 * Math.PI));
    HOOD_PIVOT_CONFIGURATION.Feedback.SensorToMechanismRatio = 1.0 / ((10.0 / 130.0) * (12.0 / 30.0));
    TURRET_CONFIGURATION.Feedback.SensorToMechanismRatio = 1.0 / ((12.0 / 58.0) * (10.0 / 90.0));

    INTAKE_SLIDE_CONFIGURATION.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    HOOD_PIVOT_CONFIGURATION.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    TURRET_CONFIGURATION.MotorOutput.NeutralMode = NeutralModeValue.Coast;

    INTAKE_SLIDE_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    HOOD_PIVOT_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    TURRET_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    CLIMBER_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;

  }
}
