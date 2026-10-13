// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.commands.SubCommands;
import frc.robot.constants.ConstFreeSpin;
import frc.robot.subsystems.StateMachine.RobotState;

public class ShootingOnFly extends Command {
  /** Creates a new ShootingOnFly. */
  public ShootingOnFly() {
    addRequirements(RobotContainer.stateMachineInstance);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.stateMachineInstance.setRobotState(RobotState.SHOOTING_ON_FLY);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SubCommands.shootingOnFly(false);
    RobotContainer.freeSpinInstance.setTransferBeltPercentOutput(ConstFreeSpin.TRANSFER_BELT_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setAgitatorPercentOutput(ConstFreeSpin.AGITATOR_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setIntakeRollersPercentOutput(ConstFreeSpin.INTAKE_ROLLER_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setTransferRampPercentOutput(ConstFreeSpin.TRANSFER_RAMP_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setHotdogRollersPercentOutput(ConstFreeSpin.HOTDOG_ROLLERS_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.getHotdogRollersVoltage();
    RobotContainer.freeSpinInstance.getTransferBeltVoltage();
    RobotContainer.freeSpinInstance.getTransferRampVoltage();
    RobotContainer.freeSpinInstance.getAgitatorVoltage();
    if (RobotContainer.freeSpinInstance.getHotdogRollersVoltage() > ConstFreeSpin.MAX_VOLTAGE_THRESHOLD ||
        RobotContainer.freeSpinInstance.getTransferBeltVoltage() > ConstFreeSpin.MAX_VOLTAGE_THRESHOLD ||
        RobotContainer.freeSpinInstance.getTransferRampVoltage() > ConstFreeSpin.MAX_VOLTAGE_THRESHOLD ||
        RobotContainer.freeSpinInstance.getAgitatorVoltage() > ConstFreeSpin.MAX_VOLTAGE_THRESHOLD) {
      RobotContainer.freeSpinInstance
          .setTransferBeltPercentOutput(ConstFreeSpin.REVERSING_TRANSFER_BELT_PERCENT_OUTPUT);
      RobotContainer.freeSpinInstance.setAgitatorPercentOutput(ConstFreeSpin.REVERSING_AGITATOR_PERCENT_OUTPUT);
      RobotContainer.freeSpinInstance
          .setTransferRampPercentOutput(ConstFreeSpin.REVERSING_TRANSFER_RAMP_PERCENT_OUTPUT);
      RobotContainer.freeSpinInstance
          .setHotdogRollersPercentOutput(ConstFreeSpin.REVERSING_HOTDOG_ROLLERS_PERCENT_OUTPUT);
    } else {
      RobotContainer.freeSpinInstance.setTransferBeltPercentOutput(ConstFreeSpin.TRANSFER_BELT_PERCENT_OUTPUT);
      RobotContainer.freeSpinInstance.setAgitatorPercentOutput(ConstFreeSpin.AGITATOR_PERCENT_OUTPUT);
      RobotContainer.freeSpinInstance.setIntakeRollersPercentOutput(ConstFreeSpin.INTAKE_ROLLER_PERCENT_OUTPUT);
      RobotContainer.freeSpinInstance.setTransferRampPercentOutput(ConstFreeSpin.TRANSFER_RAMP_PERCENT_OUTPUT);
      RobotContainer.freeSpinInstance.setHotdogRollersPercentOutput(ConstFreeSpin.HOTDOG_ROLLERS_PERCENT_OUTPUT);
    }
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
