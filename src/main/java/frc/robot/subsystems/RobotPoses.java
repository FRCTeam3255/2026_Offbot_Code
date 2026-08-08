// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.units.Units;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.FieldObject2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.constants.ConstField;

@Logged
public class RobotPoses extends SubsystemBase {
  /** Creates a new RobotPoses. */
  boolean isOurShift = false;
  Pose2d target = Pose2d.kZero;
  Field2d field2d = new Field2d();
  FieldObject2d robotObject = field2d.getObject("Robot");

  Pose3d modelDrivetrain = Pose3d.kZero;
  Pose3d model0Intake = Pose3d.kZero;
  Pose3d model1Turret = Pose3d.kZero;
  Pose3d model2Hood = Pose3d.kZero;

  Transform3d intakeTransform3d = new Transform3d();
  Rotation3d turretRotation3d = Rotation3d.kZero;
  Rotation3d hoodRotation3d = Rotation3d.kZero;

  Transform3d turretPivotPoint = Transform3d.kZero;
  Transform3d hoodPivotPoint = Transform3d.kZero;

  public RobotPoses() {
    SmartDashboard.putData("Field", field2d);
  }

  @Override
  public void periodic() {
    intakeTransform3d = new Transform3d(
        RobotContainer.positionalInstance.lastDesiredIntakePosition,
        Units.Inches.zero(),
        Units.Inches.zero(),
        Rotation3d.kZero);

    turretRotation3d = new Rotation3d(
        Units.Degrees.zero(),
        Units.Degrees.zero(),
        RobotContainer.positionalInstance.lastDesiredTurretAngle);

    hoodRotation3d = new Rotation3d(
        Units.Degrees.zero(),
        RobotContainer.positionalInstance.lastDesiredHoodPivotAngle,
        Units.Degrees.zero());

    robotObject.setPose(RobotContainer.drivetrainInstance.getPose());
    // This method will be called once per scheduler run

    // Robot Positions
    modelDrivetrain = new Pose3d(RobotContainer.drivetrainInstance.getPose());
    model0Intake = Pose3d.kZero.transformBy(intakeTransform3d);
    model1Turret = Pose3d.kZero.rotateAround(
        Pose3d.kZero.plus(turretPivotPoint).getTranslation(), turretRotation3d);
    model2Hood = Pose3d.kZero
        .rotateAround(Pose3d.kZero.plus(hoodPivotPoint).getTranslation(), hoodRotation3d);

    target = isOurShift ? getHub() : getPass();
  }

  public Pose2d getHub() {
    return ConstField.FieldElementGroups.HUB_POSE_SET.getAlliancePoses().get(0);
  }

  public Pose2d getPass() {
    return ConstField.FieldElementGroups.PASS_TO_CORNER_POSE.getAlliancePoses().get(0);
  }

  public Pose2d getTarget() {
    return target;
  }
}
