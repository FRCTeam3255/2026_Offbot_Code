// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states.turretStates;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.SubCommands;
import frc.robot.constants.ConstFreeSpin;
import frc.robot.subsystems.Hi.TurretState;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TurretShootingOnFly extends Command {
  /** Creates a new TurretShootingOnFly. */
  public TurretShootingOnFly() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.turretStateMachineInstance);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.turretStateMachineInstance.setTurretState(TurretState.SHOOTING_ON_FLY);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SubCommands.ShootingOnFly(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.freeSpinInstance.setTransferBeltPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setAgitatorPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setIntakeRollersPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setTransferRampPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setFlywheelPercentOutput(ConstFreeSpin.STOP);
    RobotContainer.freeSpinInstance.setHotdogRollersPercentOutput(ConstFreeSpin.STOP);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
