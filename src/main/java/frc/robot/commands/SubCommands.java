package frc.robot.commands;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.Time;
import frc.robot.RobotContainer;

public class SubCommands {
  public static void ShootingOnFly(boolean controlTurret) {
    Pose2d estimatedPoseOverTime = Pose2d.kZero;
    Distance distanceToTarget = Inches.zero();
    Pose2d target = Pose2d.kZero;
    Time TOF;
    target = RobotContainer.robotPose.getTarget();
    estimatedPoseOverTime = RobotContainer.drivetrainInstance.getEstimatedPoseOverTime();
    // estimatedPoseOverTime = RobotContainer.drivetrainInstance.getPose();
    distanceToTarget = Meters.of(estimatedPoseOverTime.getTranslation().getDistance(target.getTranslation()));
    TOF = RobotContainer.positionalInstance.getMappedTOF(distanceToTarget);
    RobotContainer.positionalInstance.timeOfFlight = TOF;
    if (controlTurret) {
      RobotContainer.positionalInstance.setTurretAngle(RobotContainer.drivetrainInstance
          .snapToTarget(
              estimatedPoseOverTime
                  .transformBy(RobotContainer.robotPose.turretPivotTransform2d),
              target)
          .minus(Degrees.of(180))
          .minus(RobotContainer.drivetrainInstance.getDrivetrainRotation()));
    } else {
      RobotContainer.positionalInstance
          .setHoodPivotAngle(RobotContainer.positionalInstance.getMappedHoodAngle(distanceToTarget));

      RobotContainer.freeSpinInstance
          .setFlywheelVelocity(RobotContainer.freeSpinInstance.getMappedFlywheelSpeed(distanceToTarget));
    }
  }
}
