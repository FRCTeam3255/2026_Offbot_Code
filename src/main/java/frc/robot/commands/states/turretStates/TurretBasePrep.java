// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states.turretStates;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.constants.ConstPositional;
import frc.robot.subsystems.TurretStateMachine.TurretState;;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TurretBasePrep extends Command {
  Angle commandTurretAngle;
  TurretState commandTurretState;

  /** Creates a new TurretBasePrep. */
  public TurretBasePrep(Angle inputTurretAngle, TurretState inputState) {
    // Use addRequirements() here to declare subsystem dependencies.
    commandTurretAngle = inputTurretAngle;
    commandTurretState = inputState;
    addRequirements(RobotContainer.turretStateMachineInstance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.positionalInstance.setTurretAngle(commandTurretAngle);
    RobotContainer.turretStateMachineInstance.setTurretState(commandTurretState);
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
    return RobotContainer.positionalInstance.getTurretAngle().isNear(commandTurretAngle,
        ConstPositional.TURRET_TOLERANCE);
  }
}
