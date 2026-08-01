// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states.presetLocations;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.StateMachine.RobotState;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class BasePreps extends Command {
  Angle commandTurretAngle;
  Angle commandHoodAngle;
  AngularVelocity commandFlywheelVelocity;
  RobotState commandState;

  /** Creates a new BasePreps. */
  public BasePreps(Angle inputTurretAngle, Angle inputHoodAngle, AngularVelocity inputFlywheelVelocity,
      RobotState inputState) {
    commandTurretAngle = inputTurretAngle;
    commandHoodAngle = inputHoodAngle;
    commandFlywheelVelocity = inputFlywheelVelocity;
    commandState = inputState;
    addRequirements(RobotContainer.stateMachineInstance);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.positionalInstance.setTurretAngle(commandTurretAngle);
    RobotContainer.positionalInstance.setHoodPivotAngle(commandHoodAngle);
    RobotContainer.freeSpinInstance.setFlywheelVelocity(commandFlywheelVelocity);
    RobotContainer.stateMachineInstance.setRobotState(commandState);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
