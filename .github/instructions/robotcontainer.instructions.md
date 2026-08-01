---
applyTo: "src/main/java/frc/robot/RobotContainer.java"
---

# RobotContainer.java Instructions

`RobotContainer.java` is the top-level composition class — it instantiates all subsystems, declares all command objects, and configures all button-to-command bindings.

## Subsystem Declarations

Each subsystem must be declared as a `public static final` instance with an `*Instance` suffix, immediately followed by a `private final` logged reference pointing to the same object:

```java
public static final FreeSpin freeSpinInstance = new FreeSpin();
private final FreeSpin loggedFreeSpinInstance = freeSpinInstance;

public static final Positional positionalInstance = new Positional();
private final Positional loggedPositionalInstance = positionalInstance;
```

- The `loggedXxxInstance` field enables Epilogue's `@Logged` annotation on `RobotContainer` to automatically log subsystem state.
- Do not add `@NotLogged` to the logged reference fields — that would defeat their purpose.
- Do not instantiate subsystems anywhere else in the codebase.

## Controller Declarations

```java
private final SN_XboxController conDriver = new SN_XboxController(controllerIDs.DRIVER_USB);
private final SN_XboxController conOperator = new SN_XboxController(controllerIDs.OPERATOR_USB);
```

- Controller fields are `private final`.
- Name: `con` + role (e.g. `conDriver`, `conOperator`).
- Always reference `controllerIDs` from `DeviceIDs` — never use a raw integer.

## State Transition Command Declarations

State transition commands must:
- Be `SCREAMING_SNAKE_CASE`.
- Be prefixed with `TRY_`.
- Use `Commands.deferredProxy(() -> stateMachineInstance.tryState(RobotState.<STATE>))`.

```java
Command TRY_INTAKING = Commands.deferredProxy(
    () -> stateMachineInstance.tryState(RobotState.INTAKING));
Command TRY_NONE = Commands.deferredProxy(
    () -> stateMachineInstance.tryState(RobotState.NONE));
```

Non-state commands (drive modes) are also `SCREAMING_SNAKE_CASE` with a descriptive name (no `TRY_` prefix):

```java
Command MANUAL = new DeferredCommand(
    driverStateMachineInstance.tryState(DriverState.MANUAL, ...), Set.of(...));
```

## Constructor

The constructor must call these methods in order:
1. Set controller deadbands (e.g. `conDriver.setLeftDeadband(...)`)
2. Set default commands (e.g. `driverStateMachineInstance.setDefaultCommand(MANUAL)`)
3. `configDriverBindings()`
4. `configOperatorBindings()`
5. `configAutonomous()`
6. Any global hardware setup (e.g. `RobotController.setBrownoutVoltage(5.5)`)

## Binding Methods

- `configDriverBindings()` — `private`, no parameters, configures all driver controller bindings.
- `configOperatorBindings()` — `private`, no parameters, configures all operator controller bindings.
- `configAutonomous()` — `private` or package-private, configures `AutoFactory`, registers autonomous routines, and sets up `SendableChooser`.

Never define bindings inline in the constructor.

## Autonomous Configuration

- The `AutoFactory` is the single factory for all Choreo trajectory commands.
- Autonomous routines are registered with `autoChooser.addOption()`.
- Starting poses must be set via `autoFactory.resetOdometry(path.name()).ignoringDisable(true)` in an `onChange` listener.
- All trajectory references use `ChoreoTraj` enum constants — never raw strings.

## `isPracticeBot()`

- Practice bot detection uses `RobotController.getSerialNumber().equals(ConstSystem.PRACTICE_BOT_RIO_SERIAL_NUMBER)`.
- The serial number lives in `ConstSystem` — never hardcode it here.

## Common Review Flags

- Subsystem declared without the paired `loggedXxxInstance` field.
- Subsystem declared without `public static final`.
- Controller declared with a raw integer port instead of `controllerIDs.*`.
- State transition command not using `Commands.deferredProxy()`.
- State transition command missing the `TRY_` prefix.
- Bindings defined inline in the constructor instead of in `configDriverBindings()` / `configOperatorBindings()`.
- Trajectory referenced as a raw string instead of a `ChoreoTraj` constant.
- Numeric literal used for brownout voltage or deadband instead of a `ConstSystem` constant.
