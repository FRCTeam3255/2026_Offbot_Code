// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.units.measure.Time;

/** Add your docs here. */
public class ConstAuto {
  public static final Time NEUTRAL_SHOOTING_TIMEOUT = Seconds.of(1.5);
  public static final Time FIRST_NEUTRAL_TO_ALLIANCE_TRAVELING_SHOOTING_TIMEOUT = Seconds.of(1);
  public static final Time SECOND_NEUTRAL_TO_ALLIANCE_TRAVELING_SHOOTING_TIMEOUT = Seconds.of(1);
  public static final Time DEPOT_SHOOTING_TIMEOUT = Seconds.of(7);
  public static final Time OUTPOST_SHOOTING_TIMEOUT = Seconds.of(7);
}
