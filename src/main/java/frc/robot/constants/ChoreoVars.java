// spotless:off
package frc.robot.constants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.measure.*;

/**
 * Generated file containing variables defined in Choreo.
 * DO NOT MODIFY THIS FILE YOURSELF; instead, change these values
 * in the Choreo GUI.
 */
public final class ChoreoVars {
    public static final LinearVelocity intaking_velocity = Units.MetersPerSecond.of(1.524);
    public static final Angle over_bump_heading = Units.Radians.of(0.5235988);

    public static final class Poses {
        public static final Pose2d against_hub = new Pose2d(3.5825274, 4.0294271, Rotation2d.fromRadians(0));
        public static final Pose2d against_hub_neutral = new Pose2d(5.9098597, 4.062469, Rotation2d.fromRadians(0));
        public static final Pose2d against_outpost = new Pose2d(0.3974082, 0.6685812, Rotation2d.fromRadians(0));
        public static final Pose2d against_tower_inline_hub = new Pose2d(1.5485528, 4.0294271, Rotation2d.fromRadians(0));
        public static final Pose2d aligned_oside_wall = new Pose2d(1.6718733, 0.6572021, Rotation2d.fromRadians(0));
        public static final Pose2d between_tower_depot = new Pose2d(0.410383, 5.0589038, Rotation2d.fromRadians(-1.5707963));
        public static final Pose2d between_tower_outpost = new Pose2d(0.4405997, 1.9271417, Rotation2d.fromRadians(0));
        public static final Pose2d d_side_alliance_bump = new Pose2d(3.3140977, 5.3718681, Rotation2d.fromRadians(0.5235988));
        public static final Pose2d d_side_bump = new Pose2d(3.6444179, 5.9593887, Rotation2d.fromRadians(0));
        public static final Pose2d d_side_corner = new Pose2d(0.4003107, 6.9230027, Rotation2d.fromRadians(-1.5707963));
        public static final Pose2d d_side_neutral_bump = new Pose2d(5.859498, 5.3718681, Rotation2d.fromRadians(0.5235988));
        public static final Pose2d d_side_neutral_middle = new Pose2d(7.8437409, 4.5358677, Rotation2d.fromRadians(1.5707963));
        public static final Pose2d d_side_neutral_wall = new Pose2d(7.8437409, 7.6582813, Rotation2d.fromRadians(1.5707963));
        public static final Pose2d d_side_trench = new Pose2d(4.1270618, 7.6582813, Rotation2d.fromRadians(1.5707963));
        public static final Pose2d depot = new Pose2d(0.6834254, 5.9790735, Rotation2d.fromRadians(0));
        public static final Pose2d o_side_alliance_bump = new Pose2d(3.3140977, 2.6981319, Rotation2d.fromRadians(-0.5235988));
        public static final Pose2d o_side_neutral_bump = new Pose2d(5.859498, 2.6981319, Rotation2d.fromRadians(-0.5235988));
        public static final Pose2d o_side_neutral_middle = new Pose2d(7.8437409, 3.5341323, Rotation2d.fromRadians(-1.5707963));
        public static final Pose2d o_side_neutral_wall = new Pose2d(7.8437409, 0.4117187, Rotation2d.fromRadians(-1.5707963));
        public static final Pose2d o_side_trench = new Pose2d(4.1270618, 0.4117187, Rotation2d.fromRadians(-1.5707963));
        public static final Pose2d pid_end = new Pose2d(3.57341, 3.051815, Rotation2d.fromRadians(0));
        public static final Pose2d pid_start = new Pose2d(3.57341, 6.051815, Rotation2d.fromRadians(0));
    }
}
// spotless:on
