# GitHub Copilot Instructions — FRCTeam3255 Robot Code

All code in this repository is FRC (FIRST Robotics Competition) robot code written in Java using the WPILib command-based framework. Reviews and suggestions must be evaluated through the lens of FRC best practices and team conventions documented below.

Reference: [FRCTeam3255 Software Conventions](https://github.com/FRCTeam3255/Wiki/blob/main/Software/Conventions.md)

---

## Project Overview

This robot uses a **state machine architecture**. All robot behavior is coordinated through:
- `StateMachine.java` — manages the `RobotState` enum and validates state transitions
- `DriverStateMachine.java` — manages driver input and drivetrain states
- `commands/states/` — one `Command` class per `RobotState`

Motor hardware is split across exactly two generic subsystems:
- `FreeSpin.java` — all velocity-controlled (free-spinning) motors
- `Positional.java` — all position-controlled (MotionMagic) motors

All subsystem instances are declared as `public static` fields in `RobotContainer` so state commands can reference them directly.

---

## Naming Conventions

| Element | Convention | Example |
|---|---|---|
| Classes | `UpperCamelCase` | `StateMachine`, `FreeSpin` |
| Methods | `lowerCamelCase` | `setFlywheelVelocity()` |
| Variables | `lowerCamelCase` | `commandTurretAngle` |
| Constants | `SCREAMING_SNAKE_CASE` | `FLYWHEEL_CORNER_SPEED` |
| Device IDs | `SCREAMING_SNAKE_CASE` | `INTAKE_ROLLERS_WEST_CAN` |
| State commands | `TRY_` prefix + `SCREAMING_SNAKE_CASE` | `TRY_INTAKING` |
| Controller fields | `con` prefix | `conDriver`, `conOperator` |
| Subsystem instances | `*Instance` suffix | `freeSpinInstance`, `drivetrainInstance` |
| Logged subsystem refs | `logged*` | `loggedFreeSpin` |

---

## Hardcoded Numbers — Flag These

**Always flag** numeric literals that appear outside of `constants/` or `DeviceIDs.java`:
- Magic numbers in subsystem methods, commands, or `RobotContainer`
- Motor percent output values (e.g. `motor.set(0.5)`) — must reference a `SCREAMING_SNAKE_CASE` constant like `ConstFreeSpin.INTAKE_PERCENT_OUTPUT`
- PID gains, setpoints, timeouts, tolerances — all belong in the matching `Const*.java` file
- Physical quantities must use WPILib `Units` typed measures (e.g. `Angle`, `AngularVelocity`, `Distance`) — never raw `double` values

The only acceptable raw `double` for motor power is a reference to a constant with the suffix `_PERCENT_OUTPUT`.

---

## State Machine Rules

- There is **one** `RobotState` enum shared across the entire robot — never create per-mechanism enums.
- `RobotState` values must be in gerund form (`INTAKING`, `SHOOTING`, `CLIMBING`) — not imperative (`INTAKE`, `SHOOT`, `CLIMB`).
- Every state must have a corresponding class in `commands/states/` that extends `Command`.
- In any state command's `initialize()`, **`setRobotState()` must be called first** — before any hardware commands.
- State commands only require `stateMachineInstance`; hardware is driven through `RobotContainer.freeSpinInstance` and `RobotContainer.positionalInstance`.
- The `StateMachine.tryState()` method enforces legal transitions — only valid `RobotState` transitions reach hardware.

---

## Subsystem Architecture

- `FreeSpin.java` holds **all** velocity-controlled TalonFX motors. Do not put positional motors here.
- `Positional.java` holds **all** position-controlled TalonFX motors. Do not put free-spin motors here.
- Motor clusters: exactly one `...Leader` motor; all others are `...Follower`.
- `Follower` control request objects must be named with `Follower` in the name and end in `AlignedRequest` or `OpposedRequest` — **never** include compass directions (`East`, `West`, `North`, `South`) in the request object name.
- Separate motor clusters with a blank line for readability.
- Every subsystem class must have `@Logged` annotation.
- All motor configurations (TalonFX, CANcoder, Pigeon2) must come from `constants/` — no inline configuration.

---

## Constants Rules

- One `Const*.java` file per subsystem in `constants/` (e.g. `ConstFreeSpin.java`, `ConstDrivetrain.java`).
- All fields are `public static final`.
- Physical quantities use WPILib `Units` typed measures — never raw `double`.
- **Every** TalonFX motor must have a corresponding `TalonFXConfiguration` constant named `<MOTOR_NAME>_CONFIGURATION`, declared at the top of the class and fully configured in a `static {}` block.
- Nested inner classes for logical sub-groups use `SCREAMING_SNAKE_CASE` (e.g. `PRACTICE_BOT`, `AUTO_ALIGN`) — except `constControllers` in `ConstSystem` which uses `lowerCamelCase` by convention.
- Constant names follow `PURPOSE_DESCRIPTION` (e.g. `FLYWHEEL_CORNER_SPEED`, `INTAKE_PERCENT_OUTPUT`). Do not repeat the subsystem name inside the constant name — it's redundant through the class reference.

---

## DeviceIDs Rules

- All hardware port/ID mappings live in a single `DeviceIDs.java` file at the root of `frc/robot/`.
- Each subsystem has a nested inner class with `lowerCamelCase` + `IDs` suffix (e.g. `freeSpinIDs`, `drivetrainIDs`, `positionalIDs`).
- All ID constants are `SCREAMING_SNAKE_CASE`.
- CAN ID naming: `MECHANISM_POSITION_CAN` (e.g. `INTAKE_ROLLERS_WEST_CAN`).
- Non-CAN IDs: `DEVICETYPE_LOCATION_CONNECTIONTYPE` (e.g. `ENCODER_FRONT_LEFT_DIO`).
- Do not specify which subsystem an ID belongs to inside the ID name — the nested inner class already provides that context.
- CAN ID ranges must be documented in comments: drivetrain `0–9`, FreeSpin `10–29`, Positional `30–49`.

---

## Units

- Always use WPILib's `Units` class — never hardcode unit conversions as raw numbers.
- Method parameters and return types for physical quantities must use typed units (e.g. `Angle`, `AngularVelocity`, `Distance`, `LinearVelocity`) not `double`.

```java
// ❌ Bad
public void setFlywheelSpeed(double rpm) { ... }

// ✔ Good
public void setFlywheelVelocity(AngularVelocity velocity) { ... }
```

---

## Logging

- Every subsystem class must be annotated with `@Logged`.
- Fields that should be excluded from logging must be annotated with `@NotLogged`.
- Use `edu.wpi.first.epilogue.Logged` (Epilogue framework), not SmartDashboard/NetworkTables for subsystem state logging.

---

## Imports

- Prefer wildcard imports (`.*`) over listing individual classes from the same package.
- Import `frc.robot.commands.states.*` rather than listing each state command individually.

---

## RobotContainer Conventions

- Controllers: `private final SN_XboxController con<Role> = new SN_XboxController(controllerIDs.<ROLE>_USB);`
- Subsystem declarations: `public static final <Type> <type>Instance = new <Type>();` followed immediately by `private final <Type> logged<Type>Instance = <type>Instance;`
- State-transition commands: `Command TRY_<STATE> = Commands.deferredProxy(() -> stateMachineInstance.tryState(RobotState.<STATE>));`
- Bindings separated into `configDriverBindings()` and `configOperatorBindings()` — each a private method with no parameters.
- Autonomous setup in `configAutonomous()`.

---

## FRC-Specific Considerations

- `periodic()` methods should be lightweight — avoid blocking calls, heavy computation, or I/O in the scheduler loop.
- Never use `Thread.sleep()` in robot code; use WPILib scheduling (`Commands.waitSeconds()`, etc.).
- Brownout voltage is configured in `RobotContainer` constructor via `RobotController.setBrownoutVoltage()`.
- Practice bot vs. competition bot differentiation is handled via RIO serial number in `ConstSystem.PRACTICE_BOT_RIO_SERIAL_NUMBER`.
- Alliance-aware poses must use `ConstField.Pose2dAllianceSet` — never manually mirror coordinates inline.
- Trajectory names must reference `ChoreoTraj` enum constants — never use raw strings for trajectory lookup.
