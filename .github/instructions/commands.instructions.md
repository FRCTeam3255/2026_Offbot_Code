---
applyTo: "src/main/java/frc/robot/commands/**"
---

# Commands Folder Instructions

Files in `commands/` implement WPILib `Command` objects that drive robot behavior. This folder has two sub-categories:
- `commands/` root — general-purpose commands (drive modes, vision measurement, pose reset)
- `commands/states/` — one command per `RobotState` (see `states.instructions.md` for state-specific rules)

## General Command Rules

- All commands extend `Command` (or a WPILib command composition class).
- Commands must declare their subsystem dependencies with `addRequirements()` in the constructor.
- All five lifecycle methods must be present: constructor, `initialize()`, `execute()`, `end(boolean interrupted)`, `isFinished()`.
- Commands should be focused and single-purpose — one command does one thing.

## No Hardcoded Numbers

- All numeric values (speeds, angles, distances, timeouts, PID gains) must reference constants from `constants/`.
- Never use raw numeric literals like `0.5`, `90.0`, or `1000` directly in a command.

## Hardware Access

- Access subsystem hardware exclusively through the `public static` instances on `RobotContainer` (e.g. `RobotContainer.freeSpinInstance`, `RobotContainer.positionalInstance`, `RobotContainer.drivetrainInstance`).
- Never instantiate `TalonFX`, `CANcoder`, or other hardware objects directly in a command.

## Drive Commands (`DriveManual`, `PoseDrive`)

- All driver input parameters must be passed in as `DoubleSupplier` (for axes) and `BooleanSupplier` (for buttons) — not captured from `RobotContainer` directly.
- Deadband filtering happens in `RobotContainer` via `conDriver.setLeftDeadband()` — do not apply manual deadbands inside drive commands.
- Field-relative vs. robot-relative logic must come from drivetrain methods, not calculated inline in the command.

## Autonomous / Choreo Commands

- All trajectory names must reference `ChoreoTraj` enum constants — never pass raw trajectory name strings.
- Autonomous routines that follow a trajectory must use `autoFactory.trajectoryCmd(path.name()).asProxy()`.
- Always set `driverStateMachineInstance.setDriverState(DriverState.CHOREO)` when starting a Choreo path.

## `isFinished()` Behavior

- Commands that run until explicitly interrupted (e.g. drive commands, state holder commands) must return `false`.
- Commands that complete a discrete action (e.g. `ResetPose`) should return `true` after the action is done.

## Vision Commands (`AddVisionMeasurement`)

- Vision measurement commands should use `.withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming).ignoringDisable(true)` so they run even when the robot is disabled.
- All Limelight names must come from `ConstVision` — no hardcoded strings.

## `end()` Method

- If `initialize()` or `execute()` sets hardware to a non-neutral state, `end()` must restore the hardware to a safe state (e.g. stop motors, return actuators to neutral).
- Leaving motors running after a command ends is only acceptable for state commands where the next state command will explicitly re-configure them.

## Common Review Flags

- Missing `addRequirements()` in the constructor.
- Numeric literal used instead of a constant from `constants/`.
- Hardware directly instantiated inside the command.
- `isFinished()` returning `true` for an ongoing command that should run until interrupted.
- `end()` does not clean up hardware set in `initialize()` or `execute()`.
- Trajectory command using a raw string instead of a `ChoreoTraj` constant.
- Drive command capturing controller state from `RobotContainer` instead of accepting it as a `DoubleSupplier`/`BooleanSupplier` parameter.
