// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.states.turretStates.TurretNone;
import frc.robot.commands.states.turretStates.TurretPreppingDSide;
import frc.robot.commands.states.turretStates.TurretPreppingHub;
import frc.robot.commands.states.turretStates.TurretPreppingNeutralToAlliance;
import frc.robot.commands.states.turretStates.TurretPreppingOSide;
import frc.robot.commands.states.turretStates.TurretPreppingOpposingToAlliance;
import frc.robot.commands.states.turretStates.TurretPreppingTower;
import frc.robot.commands.states.turretStates.TurretPreppingTrench;
import frc.robot.commands.states.turretStates.TurretShootingOnFly;

@Logged
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

  public Command tryTurretState(TurretState desiredTurretState) {
    switch (desiredTurretState) {
      case NONE:
        switch (currentTurretState) {
          case NONE:
          case SHOOTING_ON_FLY:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretNone();
        }
        break;
      case SHOOTING_ON_FLY:
        switch (currentTurretState) {
          case NONE:
          case SHOOTING_ON_FLY:
            return new TurretShootingOnFly();
        }
        break;
      case PREPPING_TRENCH:
        switch (currentTurretState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretPreppingTrench();
        }
        break;
      case PREPPING_OSIDE:
        switch (currentTurretState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretPreppingOSide();
        }
        break;
      case PREPPING_DSIDE:
        switch (currentTurretState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretPreppingDSide();
        }
        break;
      case PREPPING_TOWER:
        switch (currentTurretState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretPreppingTower();
        }
        break;
      case PREPPING_HUB:
        switch (currentTurretState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretPreppingHub();
        }
        break;
      case PREPPING_NEUTRAL_TO_ALLIANCE:
        switch (currentTurretState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretPreppingNeutralToAlliance();
        }
        break;
      case PREPPING_OPPOSING_TO_ALLIANCE:
        switch (currentTurretState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new TurretPreppingOpposingToAlliance();
        }
        break;
    }
    return Commands
        .print("ITS SO OVER D: Invalid State Provided, Blame Eli. Attempted to go to: " + desiredTurretState.toString()
            + " while at " + currentTurretState.toString());

  }

  public enum TurretState {
    NONE,
    SHOOTING_ON_FLY,
    PREPPING_TRENCH,
    PREPPING_OSIDE,
    PREPPING_DSIDE,
    PREPPING_TOWER,
    PREPPING_HUB,
    PREPPING_NEUTRAL_TO_ALLIANCE,
    PREPPING_OPPOSING_TO_ALLIANCE,
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
