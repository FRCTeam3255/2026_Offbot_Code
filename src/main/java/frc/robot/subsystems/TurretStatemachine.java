// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretStatemachine extends SubsystemBase {
  /** Creates a new TurretStatemachine. */
  public static TurretState currentTurretState;

  public TurretStatemachine() {
    currentTurretState = TurretState.NONE;
  }

  public void setTurretState(TurretState turretState) {
    currentTurretState = turretState;
  }

  public TurretState getTurretState() {
    return currentTurretState;
  }

  public enum TurretState {
    NONE,
    SHOOTING_ON_FLY
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
