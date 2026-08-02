// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Seconds;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
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
    estimatedPoseOverTime = RobotContainer.drivetrainInstance.estimatedPoseOverTime;
    distanceToTarget = Meters.of(estimatedPoseOverTime.getTranslation().getDistance(target.getTranslation()));
    TOF = RobotContainer.positionalInstance.getMappedTOF(distanceToTarget);
    RobotContainer.positionalInstance.timeOfFlight = TOF;
    // TODO: replace with hub
    RobotContainer.positionalInstance.setTurretAngle(RobotContainer.drivetrainInstance
        .snapToTarget(estimatedPoseOverTime, Pose2d.kZero));
    RobotContainer.positionalInstance
        .setHoodPivotAngle(RobotContainer.positionalInstance.getMappedHoodAngle(distanceToTarget));
    RobotContainer.freeSpinInstance
        .setFlywheelVelocity(RobotContainer.freeSpinInstance.getMappedFlywheelSpeed(distanceToTarget));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    TOF = Seconds.zero();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
