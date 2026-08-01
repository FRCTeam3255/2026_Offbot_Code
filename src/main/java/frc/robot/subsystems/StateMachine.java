// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.states.*;

@Logged
public class StateMachine extends SubsystemBase {
  public static RobotState currentRobotState;

  /** Creates a new StateMachine. */
  public StateMachine() {
    currentRobotState = RobotState.NONE;

  }

  public void setRobotState(RobotState robotState) {
    currentRobotState = robotState;
  }

  public RobotState getRobotState() {
    return currentRobotState;
  }

  public Command tryState(RobotState desiredState) {
    switch (desiredState) {
      case NONE:
        switch (currentRobotState) {
          case NONE:
          case INTAKING:
          case EJECTING:
          case RETRACTING_INTAKE:
          case SHOOTING_ON_PRESET:
          case REVERSING_SHOOTER:
          case SHOOTING_ON_FLY:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
          case PREPPING_CLIMB:
          case UNCLIMBING:
            return new InstantCommand();
        }
        break;
      case INTAKING:
        switch (currentRobotState) {
          case NONE:
          case INTAKING:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case EJECTING:
        switch (currentRobotState) {
          case NONE:
          case EJECTING:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case RETRACTING_INTAKE:
        switch (currentRobotState) {
          case NONE:
          case RETRACTING_INTAKE:
            return new InstantCommand();
        }
        break;
      case SHOOTING_ON_PRESET:
        switch (currentRobotState) {
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case REVERSING_SHOOTER:
        switch (currentRobotState) {
          case NONE:
          case REVERSING_SHOOTER:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case SHOOTING_ON_FLY:
        switch (currentRobotState) {
          case NONE:
          case SHOOTING_ON_FLY:
            return new InstantCommand();
        }
        break;
      case PREPPING_TRENCH:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case PREPPING_OSIDE:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case PREPPING_DSIDE:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case PREPPING_TOWER:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case PREPPING_HUB:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case PREPPING_NEUTRAL_TO_ALLIANCE:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case PREPPING_OPPOSING_TO_ALLIANCE:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_TRENCH:
          case PREPPING_OSIDE:
          case PREPPING_DSIDE:
          case PREPPING_TOWER:
          case PREPPING_HUB:
          case PREPPING_NEUTRAL_TO_ALLIANCE:
          case PREPPING_OPPOSING_TO_ALLIANCE:
            return new InstantCommand();
        }
        break;
      case PREPPING_CLIMB:
        switch (currentRobotState) {
          case NONE:
          case PREPPING_CLIMB:
            return new InstantCommand();
        }
        break;
      case CLIMBING:
        switch (currentRobotState) {
          case PREPPING_CLIMB:
          case CLIMBING:
            return new InstantCommand();
        }
        break;
      case UNCLIMBING:
        switch (currentRobotState) {
          case UNCLIMBING:
          case CLIMBING:
            return new InstantCommand();
        }
        break;

    }
    return Commands
        .print("ITS SO OVER D: Invalid State Provided, Blame Eli. Attempted to go to: " + desiredState.toString()
            + " while at " + currentRobotState.toString());
  }

  public enum RobotState {
    NONE,
    INTAKING,
    EJECTING,
    RETRACTING_INTAKE,
    SHOOTING_ON_PRESET,
    REVERSING_SHOOTER,
    SHOOTING_ON_FLY,
    PREPPING_TRENCH,
    PREPPING_OSIDE,
    PREPPING_DSIDE,
    PREPPING_TOWER,
    PREPPING_HUB,
    PREPPING_NEUTRAL_TO_ALLIANCE,
    PREPPING_OPPOSING_TO_ALLIANCE,
    PREPPING_CLIMB,
    CLIMBING,
    UNCLIMBING,
    // TODO: Add other robot states as needed
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
