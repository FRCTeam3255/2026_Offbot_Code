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
    public static final Angle over_bump_heading = Units.Radians.of(0.524);

    public static final class Poses {
        public static final Pose2d against_hub = new Pose2d(3.583, 4.029, Rotation2d.kZero);
        public static final Pose2d against_hub_neutral = new Pose2d(5.91, 4.062, Rotation2d.kZero);
        public static final Pose2d against_outpost = new Pose2d(0.397, 0.669, Rotation2d.kZero);
        public static final Pose2d against_tower_inline_hub = new Pose2d(1.549, 4.029, Rotation2d.kZero);
        public static final Pose2d aligned_oside_wall = new Pose2d(1.672, 0.657, Rotation2d.kZero);
        public static final Pose2d between_tower_depot = new Pose2d(0.41, 5.059, Rotation2d.fromRadians(-1.571));
        public static final Pose2d between_tower_outpost = new Pose2d(0.441, 1.927, Rotation2d.kZero);
        public static final Pose2d d_side_alliance_bump = new Pose2d(3.314, 5.372, Rotation2d.fromRadians(0.524));
        public static final Pose2d d_side_bump = new Pose2d(3.644, 5.959, Rotation2d.kZero);
        public static final Pose2d d_side_corner = new Pose2d(0.4, 6.923, Rotation2d.fromRadians(-1.571));
        public static final Pose2d d_side_neutral_bump = new Pose2d(5.859, 5.372, Rotation2d.fromRadians(0.524));
        public static final Pose2d d_side_neutral_middle = new Pose2d(7.844, 4.536, Rotation2d.fromRadians(1.571));
        public static final Pose2d d_side_neutral_wall = new Pose2d(7.844, 7.658, Rotation2d.fromRadians(1.571));
        public static final Pose2d d_side_trench = new Pose2d(4.127, 7.658, Rotation2d.fromRadians(1.571));
        public static final Pose2d depot = new Pose2d(0.683, 5.979, Rotation2d.kZero);
        public static final Pose2d o_side_alliance_bump = new Pose2d(3.314, 2.698, Rotation2d.fromRadians(-0.524));
        public static final Pose2d o_side_neutral_bump = new Pose2d(5.859, 2.698, Rotation2d.fromRadians(-0.524));
        public static final Pose2d o_side_neutral_middle = new Pose2d(7.844, 3.534, Rotation2d.fromRadians(-1.571));
        public static final Pose2d o_side_neutral_wall = new Pose2d(7.844, 0.412, Rotation2d.fromRadians(-1.571));
        public static final Pose2d o_side_trench = new Pose2d(4.127, 0.412, Rotation2d.fromRadians(-1.571));
        public static final Pose2d pid_end = new Pose2d(3.573, 3.052, Rotation2d.kZero);
        public static final Pose2d pid_start = new Pose2d(3.573, 6.052, Rotation2d.kZero);

        private Poses() {}
    }

    private ChoreoVars() {}
}