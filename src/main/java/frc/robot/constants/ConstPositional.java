// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.Inches;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.units.measure.Distance;
import static edu.wpi.first.units.Units.Degrees;

import edu.wpi.first.units.Units;

import edu.wpi.first.units.measure.Angle;

/** Add your docs here. */
public class ConstPositional {
  // outputs
  public static final double STOP = 0;

  public static final Distance DEPLOY_INTAKE_POSITION = Inches.of(1);// TODO find actual distance

  public static final Distance RETRACT_CLIMBER_DISTANCE = Units.Inches.of(0);
  public static final Distance DEPLOY_CLIMBER_DISTANCE = Units.Inches.of(10);
  public static final Distance CLIMBER_TOLERANCE = Units.Inches.of(0.5);
  public static final int SLOW_CLIMBER_PID = 0;
  public static final int FAST_CLIMBER_PID = 1;
  public static final Distance DEPLOY_INTAKE_SLIDE_DISTANCE = Units.Inches.of(20);

  public static final int SLOW_INTAKE_SLIDE_PID = 0;
  public static final int FAST_INTAKE_SLIDE_PID = 1;

  public static final Angle TURRET_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance
  public static final Angle HOOD_TOLERANCE = Degrees.of(1);// TODO: find actual tolerance

  // configuration
  public static final TalonFXConfiguration INTAKE_SLIDE_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration HOOD_PIVOT_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration TURRET_CONFIGURATION = new TalonFXConfiguration();
  public static final TalonFXConfiguration CLIMBER_CONFIGURATION = new TalonFXConfiguration();

  static {
    // Configure TalonFXConfiguration objects here
  }
}
