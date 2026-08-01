// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorAlignmentValue;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DeviceIDs;
import frc.robot.constants.ConstFreeSpin;

@Logged
public class FreeSpin extends SubsystemBase {
  final TalonFX intakeRollerWestLeader = new TalonFX(DeviceIDs.freeSpinIDs.INTAKE_ROLLERS_WEST_CAN);
  final TalonFX intakeRollerEastFollower = new TalonFX(DeviceIDs.freeSpinIDs.INTAKE_ROLLERS_EAST_CAN);

  final TalonFX hotdogRollers = new TalonFX(DeviceIDs.freeSpinIDs.HOTDOG_ROLLERS_CAN);
  final TalonFX transferBelt = new TalonFX(DeviceIDs.freeSpinIDs.TRANSFER_BELT_CAN);

  final TalonFX westFlywheelLeader = new TalonFX(DeviceIDs.freeSpinIDs.FLYWHEEL_WEST_CAN);
  final TalonFX eastFlywheelFollower = new TalonFX(DeviceIDs.freeSpinIDs.FLYWHEEL_EAST_CAN);

  final TalonFX agitator = new TalonFX(DeviceIDs.freeSpinIDs.AGITATOR_CAN);
  final TalonFX transferRamp = new TalonFX(DeviceIDs.freeSpinIDs.TRANSFER_RAMP_CAN);

  Follower flywheelFollower = new Follower(westFlywheelLeader.getDeviceID(), MotorAlignmentValue.Opposed);

  Follower intakeFollower = new Follower(intakeRollerWestLeader.getDeviceID(), MotorAlignmentValue.Opposed);

  public FreeSpin() {

    intakeRollerWestLeader.getConfigurator().apply(ConstFreeSpin.INTAKE_ROLLERS_WEST_CONFIGURATION);
    intakeRollerEastFollower.getConfigurator().apply(ConstFreeSpin.INTAKE_ROLLERS_EAST_CONFIGURATION);
    hotdogRollers.getConfigurator().apply(ConstFreeSpin.HOTDOG_ROLLERS_CONFIGURATION);
    transferBelt.getConfigurator().apply(ConstFreeSpin.TRANSFER_BELT_CONFIGURATION);
    westFlywheelLeader.getConfigurator().apply(ConstFreeSpin.FLYWHEEL_WEST_CONFIGURATION);
    eastFlywheelFollower.getConfigurator().apply(ConstFreeSpin.FLYWHEEL_EAST_CONFIGURATION);
    agitator.getConfigurator().apply(ConstFreeSpin.AGITATOR_CONFIGURATION);
    transferRamp.getConfigurator().apply(ConstFreeSpin.TRANSFER_RAMP_CONFIGURATION);

  }

  final MotionMagicVelocityVoltage flywheelVelocityRequest = new MotionMagicVelocityVoltage(0);

  public void setIntakeRollersSpeed(double speed) {
    intakeRollerWestLeader.set(speed);
    intakeRollerEastFollower.setControl(intakeFollower);
  }

  public void setFlywheelSpeed(AngularVelocity speed) {
    westFlywheelLeader.setControl(flywheelVelocityRequest.withVelocity(speed));
    eastFlywheelFollower.setControl(flywheelFollower);
  }

  public AngularVelocity getFlywheelSpeed() {
    return westFlywheelLeader.getVelocity().getValue();
  }

  public void setHotdogRollersSpeed(double speed) {
    hotdogRollers.set(speed);
  }

  public void setTransferBeltSpeed(double speed) {
    transferBelt.set(speed);
  }

  public void setAgitatorSpeed(double speed) {
    agitator.set(speed);
  }

  public void setTransferRampSpeed(double speed) {
    transferRamp.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
