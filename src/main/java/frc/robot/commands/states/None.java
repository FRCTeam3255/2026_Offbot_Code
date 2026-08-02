// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.constants.ConstFreeSpin;
import frc.robot.constants.ConstPositional;
import frc.robot.subsystems.StateMachine.RobotState;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class None extends Command {
  /** Creates a new None. */
  public None() {
    addRequirements(RobotContainer.stateMachineInstance);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.stateMachineInstance.setRobotState(RobotState.NONE);
    RobotContainer.freeSpinInstance.setAgitatorPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setHotdogRollersPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setIntakeRollersPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setTransferRampPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setTransferBeltPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setFlywheelPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.positionalInstance.setHoodPivotAngle(ConstPositional.HOOD_ANGLE_RETRACT);
    RobotContainer.positionalInstance.setClimberPosition(ConstPositional.RETRACT_CLIMBER_DISTANCE,
        ConstPositional.FAST_CLIMBER_PID);

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
