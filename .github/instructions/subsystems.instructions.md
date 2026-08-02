---
applyTo: "src/main/java/frc/robot/subsystems/**"
---

# Subsystems Folder Instructions

Files in `subsystems/` define the robot's subsystems. Each subsystem owns a set of hardware devices and exposes methods to control or read them. The two generic motor-driver subsystems (`FreeSpin.java` and `Positional.java`) hold all TalonFX motors.

## Subsystem Architecture Rules

- `FreeSpin.java` holds **all** velocity-controlled TalonFX motors (flywheel, intake rollers, transfer belts, etc.). Do not add positional motors here.
- `Positional.java` holds **all** position-controlled TalonFX motors (hood pivot, turret, intake slide, climber, etc.). Do not add free-spin motors here.
- Do not create new motor-type subsystems — all motors belong in one of these two classes.
- Other subsystems (`Drivetrain`, `Vision`, `StateMachine`, `DriverStateMachine`, `RobotPoses`, `Telemetry`) have specific responsibilities and should not hold TalonFX motor instances (except `Drivetrain`).

## Required Class Annotations

- Every subsystem class must have `@Logged` on the class declaration (Epilogue logging framework).
- Fields excluded from logging must have `@NotLogged`.

```java
@Logged
public class FreeSpin extends SubsystemBase {
    @NotLogged
    private boolean someTransientFlag;
}
```

## Motor Declarations

- All `TalonFX` fields must be `final` and initialized inline with the matching ID from `DeviceIDs`.
- Naming: motor clusters have exactly one `...Leader` and one or more `...Follower` motors.
- Separate motor clusters with a blank line for readability.

```java
// ✔ Correct cluster separation and naming
final TalonFX intakeRollersWestLeader = new TalonFX(DeviceIDs.freeSpinIDs.INTAKE_ROLLERS_WEST_CAN);
final TalonFX intakeRollersEastFollower = new TalonFX(DeviceIDs.freeSpinIDs.INTAKE_ROLLERS_EAST_CAN);

final TalonFX flywheelWestLeader = new TalonFX(DeviceIDs.freeSpinIDs.FLYWHEEL_WEST_CAN);
final TalonFX flywheelEastFollower = new TalonFX(DeviceIDs.freeSpinIDs.FLYWHEEL_EAST_CAN);
```

## Follower Request Objects

- `Follower` control request objects must have `Follower` in the name and end in `AlignedRequest` or `OpposedRequest`.
- Never include compass directions (`East`, `West`, `North`, `South`) in the request object name — the motor variable already communicates which hardware is following.

```java
// ❌ Bad
Follower flywheelFollower = new Follower(flywheelWestLeader.getDeviceID(), MotorAlignmentValue.Opposed);

// ✔ Good
Follower flywheelFollowerOpposedRequest = new Follower(flywheelWestLeader.getDeviceID(), MotorAlignmentValue.Opposed);
```

## Constructor Rules

- The constructor applies configurations from `constants/` using `motor.getConfigurator().apply(Const*.MOTOR_CONFIGURATION)`.
- No numeric literals in the constructor — every value must come from `constants/`.
- No configuration logic in the constructor — all `TalonFXConfiguration` setup lives in the matching `Const*.java` `static {}` block.

## Method Conventions

- Method names are `lowerCamelCase`.
- Methods that set motor output use `set*` prefix (e.g. `setFlywheelVelocity`, `setIntakeRollersPercentOutput`).
- Methods that read sensor values use `get*` prefix (e.g. `getFlywheelVelocity`, `getIntakeSlidePosition`).
- Parameters and return types for physical quantities use WPILib typed measures (`AngularVelocity`, `Angle`, `Distance`), not raw `double`.

```java
// ❌ Bad
public void setFlywheelSpeed(double rpm) { ... }
public double getFlywheelSpeed() { ... }

// ✔ Good
public void setFlywheelVelocity(AngularVelocity velocity) { ... }
public AngularVelocity getFlywheelVelocity() { ... }
```

## `periodic()` Method

- `periodic()` must be present and lightweight.
- Never call blocking I/O, perform heavy computation, or sleep in `periodic()`.
- Logging happens automatically through Epilogue — do not manually push values to SmartDashboard in `periodic()` unless there is a specific telemetry reason.

## StateMachine Subsystem

- `StateMachine.java` owns the `RobotState` enum.
- There is **one** shared `RobotState` enum for the entire robot — do not create additional state enums per mechanism.
- `tryState()` enforces valid transitions — any new state added to `RobotState` must have corresponding `case` entries in `tryState()`.
- The fallback `Commands.print(...)` message is intentional and should be preserved for debugging invalid transitions.
- `currentRobotState` is `public static` for read access from state commands; it should only be mutated through `setRobotState()`.

## DriverStateMachine Subsystem

- `DriverStateMachine.java` follows the same pattern as `StateMachine` but for driver control modes.
- `tryState()` returns a `Supplier<Command>` — not a `Command` directly — to support deferred instantiation with `DeferredCommand`.
- The `CHOREO` driver state is set externally by `RobotContainer.runPath()` when an autonomous trajectory starts.

## Vision Subsystem

- All Limelight names must come from `ConstVision` — never use raw string literals for limelight names.
- `MegaTag2` mode and IMU assist are toggled via `setMegaTag2()` and `setIMUAssistMode()` — not configured inline.

## Common Review Flags

- Missing `@Logged` on a subsystem class.
- Motor added to the wrong subsystem (`FreeSpin` vs. `Positional`).
- Numeric literals in motor declarations or the constructor (must reference `constants/`).
- Follower request object name includes compass direction or lacks `AlignedRequest`/`OpposedRequest` suffix.
- Motor clusters not separated by a blank line.
- Physical quantity returned or accepted as `double` instead of a WPILib typed measure.
- Configuration logic appearing in the constructor instead of the corresponding `Const*.java` `static {}` block.
- New `RobotState` value added to the enum but `tryState()` not updated to handle it.
