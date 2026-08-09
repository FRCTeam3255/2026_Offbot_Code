// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Seconds;

import com.ctre.phoenix6.controls.MotionMagicExpoVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DeviceIDs;
import frc.robot.Robot;
import frc.robot.constants.ConstPositional;

@Logged
public class Positional extends SubsystemBase {

  final TalonFX intakeSlide = new TalonFX(DeviceIDs.positionalIDs.INTAKE_SLIDE_CAN);
  final TalonFX hoodPivot = new TalonFX(DeviceIDs.positionalIDs.HOOD_PIVOT_CAN);
  final TalonFX turret = new TalonFX(DeviceIDs.positionalIDs.TURRET_CAN);
  final TalonFX climber = new TalonFX(DeviceIDs.positionalIDs.CLIMBER_CAN);

  MotionMagicExpoVoltage intakeSlideMotionRequest = new MotionMagicExpoVoltage(0);
  MotionMagicExpoVoltage hoodPivotMotionRequest = new MotionMagicExpoVoltage(0);
  MotionMagicExpoVoltage turretMotionRequest = new MotionMagicExpoVoltage(0);
  MotionMagicExpoVoltage climberMotionRequest = new MotionMagicExpoVoltage(0);

  public Time timeOfFlight = Seconds.zero();
  public Angle lastDesiredHoodPivotAngle = Degrees.zero();
  public Angle lastDesiredTurretAngle = Degrees.zero();
  public Distance lastDesiredIntakePosition = Inches.zero();
  public Distance lastDesiredClimberPosition = Inches.zero();

  public Positional() {
    intakeSlide.getConfigurator().apply(ConstPositional.INTAKE_SLIDE_CONFIGURATION);
    hoodPivot.getConfigurator().apply(ConstPositional.HOOD_PIVOT_CONFIGURATION);
    turret.getConfigurator().apply(ConstPositional.TURRET_CONFIGURATION);
    climber.getConfigurator().apply(ConstPositional.CLIMBER_CONFIGURATION);

  }

  public void setIntakePosition(Distance setPoint, int slot) {
    intakeSlide.setControl(intakeSlideMotionRequest.withPosition(setPoint.in(Units.Inches)).withSlot(slot));
    lastDesiredIntakePosition = setPoint;
  }

  public void setClimberPosition(Distance setPoint, int slot) {
    climber.setControl(climberMotionRequest.withPosition(setPoint.in(Units.Inches)).withSlot(slot));
    lastDesiredClimberPosition = setPoint;
  }

  public void setHoodPivotAngle(Angle setPoint) {
    hoodPivot.setControl(hoodPivotMotionRequest.withPosition(setPoint));
    lastDesiredHoodPivotAngle = setPoint;
  }

  public void setTurretAngle(Angle setPoint) {
    lastDesiredTurretAngle = getWrappedTurretAngle(setPoint);
    turret.setControl(turretMotionRequest.withPosition(lastDesiredTurretAngle));
  }

  public Angle getHoodPivotAngle() {
    if (Robot.isSimulation()) {
      return lastDesiredHoodPivotAngle;
    }
    return hoodPivot.getPosition().getValue();
  }

  public Angle getTurretAngle() {
    if (Robot.isSimulation()) {
      return lastDesiredTurretAngle;
    }
    return turret.getPosition().getValue();
  }

  public Distance getIntakeSlidePosition() {
    if (Robot.isSimulation()) {
      return lastDesiredIntakePosition;
    }
    return Units.Inches.of(intakeSlide.getPosition().getValueAsDouble());
  }

  public Distance getClimberPosition() {
    if (Robot.isSimulation()) {
      return lastDesiredClimberPosition;
    }
    return Units.Inches.of(climber.getPosition().getValueAsDouble());
  }

  public Angle getMappedHoodAngle(Distance distance) {
    return Degrees.of(ConstPositional.hoodAngleMap.get(distance.in(Inches)));
  }

  public Time getMappedTOF(Distance distance) {
    return Seconds.of(ConstPositional.timeOfFlightMap.get(distance.in(Inches)));
  }

  public Angle getWrappedTurretAngle(Angle desired) {
    Angle max = ConstPositional.MAX_TURRET_ANGLE, min = ConstPositional.MIN_TURRET_ANGLE;
    if (desired.gt(max) || desired.lt(min)) {
      desired = Units.Degrees.of(lastDesiredTurretAngle.magnitude() % 360);
    }
    return desired;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
