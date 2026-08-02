// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states.presetLocations;

import frc.robot.constants.ConstFreeSpin;
import frc.robot.constants.ConstPositional;
import frc.robot.subsystems.StateMachine;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class PreppingTower extends BasePreps {
  /** Creates a new PreppingTower. */
  public PreppingTower() {
    super(ConstPositional.PREP_TOWER_TURRET,
        ConstPositional.PREP_TOWER_HOOD,
        ConstFreeSpin.PREP_TOWER_FLYWHEEL,
        StateMachine.RobotState.PREPPING_TOWER);
    // Use addRequirements() here to declare subsystem dependencies.

  }

}
