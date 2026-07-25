// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
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
          case RETRACTINGINTAKE:
          case SHOOTINGONPRESET:
          case REVERSINGSHOOTER:
          case SHOOTINGONFLY:
          case PREPPINGTRENCH:
          case PREPPINGOSIDE:
          case PREPPINGDSIDE:
          case PREPPINGTOWER:
          case PREPPINGHUB:
          case PREPPINGNEUTRALTOALLIANCE:
          case PREPPINGOPPOSINGTOALLIANCE:
          case PREPPINGCLIMB:
          case CLIMBING:
          case UNCLIMBING:
            return new None();
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
    RETRACTINGINTAKE,
    SHOOTINGONPRESET,
    REVERSINGSHOOTER,
    SHOOTINGONFLY,
    PREPPINGTRENCH,
    PREPPINGOSIDE,
    PREPPINGDSIDE,
    PREPPINGTOWER,
    PREPPINGHUB,
    PREPPINGNEUTRALTOALLIANCE,
    PREPPINGOPPOSINGTOALLIANCE,
    PREPPINGCLIMB,
    CLIMBING,
    UNCLIMBING,
    // TODO: Add other robot states as needed
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
