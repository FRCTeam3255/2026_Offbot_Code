// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.constants.ConstDrivetrain;
import frc.robot.constants.ConstField;
import frc.robot.subsystems.DriverStateMachine.DriverState;
import frc.robot.subsystems.Drivetrain;

public class DriveManual extends Command {
  DoubleSupplier xAxis, yAxis, rotationXAxis;
  boolean isOpenLoop;
  BooleanSupplier slowMode;
  Timer delayTimer = new Timer();

  public DriveManual(DoubleSupplier xAxis, DoubleSupplier yAxis,
      DoubleSupplier rotationAxis, BooleanSupplier slowMode) {
    this.xAxis = xAxis;
    this.yAxis = yAxis;
    this.rotationXAxis = rotationAxis;
    this.slowMode = slowMode;

    isOpenLoop = true;

    addRequirements(RobotContainer.driverStateMachineInstance);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    RobotContainer.driverStateMachineInstance.setDriverState(DriverState.MANUAL);
    ChassisSpeeds velocities = RobotContainer.drivetrainInstance.calculateVelocitiesFromInput(
        xAxis,
        yAxis,
        rotationXAxis,
        slowMode,
        ConstField.isRedAlliance(),
        ConstDrivetrain.SLOW_MODE_MULTIPLIER,
        ConstDrivetrain.REAL_DRIVE_SPEED,
        ConstDrivetrain.TURN_SPEED);

    if (DriverStation.isAutonomousEnabled()) {
      driveWithTargetRotation(velocities);
      return;
    }

    driveWithSticks(velocities);
    updateXbrake();
  }

  private void driveWithSticks(ChassisSpeeds velocities) {
    boolean isRotateStickHit = RobotContainer.drivetrainInstance.isStickHit(rotationXAxis,
        ConstDrivetrain.ROTATION_STICK_DEADBAND);

    if (isRotateStickHit) {
      manualRotation(velocities);
    } else {
      correctRotation(velocities);
    }
  }

  private void manualRotation(ChassisSpeeds velocities) {
    RobotContainer.drivetrainInstance.setIsManualRotationEnabled(true);
    RobotContainer.drivetrainInstance.drive(velocities);
    RobotContainer.drivetrainInstance
        .setDriveRotation(RobotContainer.drivetrainInstance.getPose().getRotation().getMeasure());
    delayTimer.reset();
  }

  private void correctRotation(ChassisSpeeds velocities) {
    delayTimer.start();
    boolean delayElapsed = delayTimer.hasElapsed(ConstDrivetrain.ROTATION_DELAY.magnitude());

    if (delayElapsed) {
      driveWithTargetRotation(velocities);
    } else {
      RobotContainer.drivetrainInstance.drive(velocities);
      RobotContainer.drivetrainInstance
          .setDriveRotation(RobotContainer.drivetrainInstance.getPose().getRotation().getMeasure());
    }
  }

  private void driveWithTargetRotation(ChassisSpeeds velocities) {
    RobotContainer.drivetrainInstance.drive(
        velocities,
        RobotContainer.drivetrainInstance.getTargetRotation(),
        ConstDrivetrain.ROTATION_PID.kP,
        ConstDrivetrain.ROTATION_PID.kI,
        ConstDrivetrain.ROTATION_PID.kD);
  }

  private void updateXbrake() {
    boolean isStickHit = RobotContainer.drivetrainInstance.isStickHit(xAxis, yAxis,
        ConstDrivetrain.ROTATION_STICK_DEADBAND)
        || RobotContainer.drivetrainInstance.isStickHit(rotationXAxis, ConstDrivetrain.ROTATION_STICK_DEADBAND);
    RobotContainer.drivetrainInstance.setXbrakeAllowed(!isStickHit);
  }

  @Override
  public void end(boolean interrupted) {
    delayTimer.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}