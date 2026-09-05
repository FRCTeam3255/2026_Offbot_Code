// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.states.turretStates;

import frc.robot.constants.ConstPositional;
import frc.robot.subsystems.TurretStateMachine.TurretState;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class TurretPreppingTrench extends TurretBasePrep {
  /** Creates a new TurretPreppingTrench. */
  public TurretPreppingTrench() {
    // Use addRequirements() here to declare subsystem dependencies.
    super(ConstPositional.PREP_TRENCH_TURRET, TurretState.PREPPING_TRENCH);
  }
}
