// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.constants.ConstFreeSpin;
import frc.robot.subsystems.StateMachine.RobotState;

public class ShootingOnFly extends Command {
  /** Creates a new ShootingOnFly. */
  Pose2d estimatedPoseOverTime = Pose2d.kZero;
  Time TOF = Seconds.zero();
  Distance distanceToTarget = Inches.zero();
  Pose2d target = Pose2d.kZero;

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
    target = RobotContainer.robotPose.getTarget();
    estimatedPoseOverTime = RobotContainer.drivetrainInstance.getEstimatedPoseOverTime();
    // estimatedPoseOverTime = RobotContainer.drivetrainInstance.getPose();
    distanceToTarget = Meters.of(estimatedPoseOverTime.getTranslation().getDistance(target.getTranslation()));
    TOF = RobotContainer.positionalInstance.getMappedTOF(distanceToTarget);
    RobotContainer.positionalInstance.timeOfFlight = TOF;

    RobotContainer.positionalInstance.setTurretAngle(RobotContainer.drivetrainInstance
        .snapToTarget(
            estimatedPoseOverTime
                .transformBy(RobotContainer.robotPose.turretPivotTransform2d),
            target)
        .minus(Degrees.of(180))
        .minus(RobotContainer.drivetrainInstance.getDrivetrainRotation()));

    RobotContainer.positionalInstance
        .setHoodPivotAngle(RobotContainer.positionalInstance.getMappedHoodAngle(distanceToTarget));

    RobotContainer.freeSpinInstance
        .setFlywheelVelocity(RobotContainer.freeSpinInstance.getMappedFlywheelSpeed(distanceToTarget));

    RobotContainer.freeSpinInstance.setTransferBeltPercentOutput(ConstFreeSpin.TRANSFER_BELT_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setAgitatorPercentOutput(ConstFreeSpin.AGITATOR_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setIntakeRollersPercentOutput(ConstFreeSpin.INTAKE_ROLLER_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setTransferRampPercentOutput(ConstFreeSpin.TRANSFER_RAMP_PERCENT_OUTPUT);
    RobotContainer.freeSpinInstance.setHotdogRollersPercentOutput(ConstFreeSpin.HOTDOG_ROLLERS_PERCENT_OUTPUT);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    TOF = Seconds.zero();
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
