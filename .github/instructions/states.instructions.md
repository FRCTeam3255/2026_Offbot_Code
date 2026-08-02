---
applyTo: "src/main/java/frc/robot/commands/states/**"
---

# State Commands Folder Instructions

Files in `commands/states/` implement the state machine. Each file corresponds to exactly one `RobotState` value and controls what the robot does while in that state.

## One Command Per State

- Every `RobotState` enum value must have exactly one corresponding `Command` class in this folder (or a sub-folder for logical groupings like `climbStates/` or `presetLocations/`).
- The class name must match the state name in `UpperCamelCase` (e.g. `RobotState.INTAKING` → `Intaking.java`).

## Required Command Structure

Every state command must:
1. `addRequirements(RobotContainer.stateMachineInstance)` in the constructor.
2. Call `RobotContainer.stateMachineInstance.setRobotState(RobotState.<STATE>)` as the **very first line** of `initialize()` — before any hardware calls.
3. Implement all five `Command` lifecycle methods: constructor, `initialize()`, `execute()`, `end(boolean interrupted)`, `isFinished()`.

```java
// ✔ Correct initialize() ordering
@Override
public void initialize() {
    RobotContainer.stateMachineInstance.setRobotState(RobotState.INTAKING); // ← FIRST
    RobotContainer.freeSpinInstance.setIntakeRollersPercentOutput(ConstFreeSpin.INTAKE_PERCENT_OUTPUT);
}
```

```java
// ❌ Wrong — hardware called before state is set
@Override
public void initialize() {
    RobotContainer.freeSpinInstance.setIntakeRollersPercentOutput(ConstFreeSpin.INTAKE_PERCENT_OUTPUT);
    RobotContainer.stateMachineInstance.setRobotState(RobotState.INTAKING); // ← too late
}
```

## `isFinished()` Return Value

- Can return `true` if the state is truly a one-shot action that completes on its own.
- Commands can also return true if they are setting positions and the motors reach their positions (using .isNear)
```java
@Override
public boolean isFinished() {
  return RobotContainer.positionalInstance.getClimberPosition()
      .isNear(RobotContainer.positionalInstance.lastDesiredClimberPosition, ConstPositional.CLIMBER_TOLERANCE);
}
```

## No Hardcoded Numbers

- All motor outputs, setpoints, velocities, and angles must reference constants from `constants/ConstFreeSpin.java` or `constants/ConstPositional.java`.
- Never use raw numeric literals (e.g. `0.8`, `45.0`, `3000`) directly in a state command.

## BaseState Pattern (for Prep states)

When multiple prep states share the same structure (turret angle + hood angle + flywheel speed), extract a `Base*` command:

- Instance variables (used by the command) → `command` prefix: `commandTurretAngle`, `commandFlywheelVelocity`
- Constructor parameters (passed in by the caller) → `input` prefix: `inputTurretAngle`, `inputFlywheelVelocity`

```java
public class BasePreps extends Command {
    Angle commandTurretAngle;
    AngularVelocity commandFlywheelVelocity;
    RobotState commandState;

    public BasePreps(Angle inputTurretAngle, AngularVelocity inputFlywheelVelocity, RobotState inputState) {
        commandTurretAngle = inputTurretAngle;
        commandFlywheelVelocity = inputFlywheelVelocity;
        commandState = inputState;
        addRequirements(RobotContainer.stateMachineInstance);
    }

    @Override
    public void initialize() {
        RobotContainer.stateMachineInstance.setRobotState(commandState); // ← FIRST
        RobotContainer.positionalInstance.setTurretAngle(commandTurretAngle);
        RobotContainer.freeSpinInstance.setFlywheelVelocity(commandFlywheelVelocity);
    }
}
```

## RobotState Naming

- State enum values must use gerund form (`-ing`) — they represent ongoing actions.
- ✔ `INTAKING`, `SHOOTING_ON_FLY`, `PREPPING_TRENCH`, `CLIMBING`
- ❌ `INTAKE`, `SHOOT`, `CLIMB`, `PREP_TRENCH`

## Hardware Access Pattern

State commands access hardware exclusively through the `public static` instances on `RobotContainer`:
- `RobotContainer.freeSpinInstance` for velocity-controlled motors
- `RobotContainer.positionalInstance` for position-controlled motors
- `RobotContainer.stateMachineInstance` for state transitions

Never instantiate subsystems or `TalonFX` objects directly in a command.

## Common Review Flags

- `setRobotState()` is not the first call in `initialize()`.
- Hardware constants are hardcoded as numeric literals instead of referencing `constants/`.
- Command constructor does not call `addRequirements(RobotContainer.stateMachineInstance)`.
- `isFinished()` returns `true` for an ongoing state that should run until interrupted.
- `BasePreps` sub-classes don't follow `command`/`input` variable prefix convention.
- A new `RobotState` was added to the enum but no corresponding command class was created.
- A new state command was added but `StateMachine.tryState()` was not updated with the new transition logic.
