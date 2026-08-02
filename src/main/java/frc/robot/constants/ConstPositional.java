// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.units.measure.Distance;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.units.Units;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.units.measure.Angle;

/** Add your docs here. */
public class ConstPositional {
  // outputs
  public static final double STOP = 0;

  public static final Angle HOOD_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance

  public final static InterpolatingDoubleTreeMap hoodAngleMap = new InterpolatingDoubleTreeMap();
  public final static InterpolatingDoubleTreeMap timeOfFlightMap = new InterpolatingDoubleTreeMap();
  public static final Angle TURRET_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance

  public static final Distance RETRACT_CLIMBER_DISTANCE = Units.Inches.of(0);
  public static final Distance DEPLOY_CLIMBER_DISTANCE = Units.Inches.of(10);
  public static final Distance CLIMBER_TOLERANCE = Units.Inches.of(0.5);
  public static final int SLOW_CLIMBER_PID = 0;
  public static final int FAST_CLIMBER_PID = 1;
  public static final Distance DEPLOY_INTAKE_SLIDE_DISTANCE = Units.Inches.of(20);

  public static final int SLOW_INTAKE_SLIDE_PID = 0;
  public static final int FAST_INTAKE_SLIDE_PID = 1;

  public static final Angle PREP_D_SIDE_HOOD = Degrees.of(1);// TODO: find actual value
  public static final Angle PREP_D_SIDE_TURRET = Degrees.of(1);// TODO: find actual value

  public static final Angle PREP_O_SIDE_HOOD = Degrees.of(1);// TODO: find actual value
  public static final Angle PREP_O_SIDE_TURRET = Degrees.of(1);// TODO: find actual value

  public static final Angle PREP_HUB_HOOD = Degrees.of(1);// TODO: find actual value
  public static final Angle PREP_HUB_TURRET = Degrees.of(1);// TODO: find actual value

  public static final Angle PREP_NEUTRAL_TO_ALLIANCE_HOOD = Degrees.of(1);// TODO: find actual value
  public static final Angle PREP_NEUTRAL_TO_ALLIANCE_TURRET = Degrees.of(1);// TODO: find actual value

  public static final Angle PREP_OPPOSING_TO_ALLIANCE_HOOD = Degrees.of(1);// TODO: find actual value
  public static final Angle PREP_OPPOSING_TO_ALLIANCE_TURRET = Degrees.of(1);// TODO: find actual value

  public static final Angle PREP_TOWER_HOOD = Degrees.of(1);// TODO: find actual value
  public static final Angle PREP_TOWER_TURRET = Degrees.of(1);// TODO: find actual value

  public static final Angle PREP_TRENCH_HOOD = Degrees.of(1);// TODO: find actual value
  public static final Angle PREP_TRENCH_TURRET = Degrees.of(1);// TODO: find actual value

  // configuration
  public static final TalonFXConfiguration INTAKE_SLIDE_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration HOOD_PIVOT_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TURRET_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration CLIMBER_CONFIGURATION = new TalonFXConfiguration();

  static {
    // TODO: Tune
    // ---- Hood Angle Map ---- //
    hoodAngleMap.put(Inches.of(60).in(Inches), Degrees.of(1).in(Degrees));
    hoodAngleMap.put(Inches.of(50).in(Inches), Degrees.of(1).in(Degrees));

    // ---- Time Of Flight Map ---- //
    timeOfFlightMap.put(Inches.of(60).in(Inches), Seconds.of(0).in(Seconds));
    timeOfFlightMap.put(Inches.of(50).in(Inches), Seconds.of(1).in(Seconds));

    // Configure TalonFXConfiguration objects here
  }
}
