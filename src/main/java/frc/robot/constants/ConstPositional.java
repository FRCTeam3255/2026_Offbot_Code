// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Seconds;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.units.measure.Angle;

/** Add your docs here. */
public class ConstPositional {
  // outputs
  public static final double STOP = 0;
  public static final Angle TURRET_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance
  public static final Angle HOOD_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance

  public final static InterpolatingDoubleTreeMap hoodAngleMap = new InterpolatingDoubleTreeMap();
  public final static InterpolatingDoubleTreeMap timeOfFlightMap = new InterpolatingDoubleTreeMap();

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
