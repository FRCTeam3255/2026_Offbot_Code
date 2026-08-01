---
applyTo: "src/main/java/frc/robot/constants/**"
---

# Constants Folder Instructions

Files in `constants/` define all static configuration values for the robot. Every number that controls hardware behavior — setpoints, gains, speeds, positions, timeouts — must live here.

## File Naming & Structure

- One file per subsystem: `Const<SubsystemName>.java` (e.g. `ConstFreeSpin.java`, `ConstPositional.java`).
- File is a `public class` (not `final`, unless it is `ConstSystem`) with no constructor.
- `ConstSystem.java` is `public final class` and holds cross-subsystem constants (controller deadbands, RIO serial numbers, etc.).

## Field Declarations

- All fields must be `public static final`.
- All field names must be `SCREAMING_SNAKE_CASE`.
- Use WPILib `Units` typed measures for **all** physical quantities — no raw `double` for speeds, angles, distances, or times.
  - `AngularVelocity`, `Angle`, `Distance`, `LinearVelocity`, `Time`, `Voltage`, `Current`, etc.
- Raw `double` is only acceptable for dimensionless ratios or motor percent output values (suffix `_PERCENT_OUTPUT`).

## TalonFX Configuration Requirement

- **Every** TalonFX motor in the project **must** have a corresponding `TalonFXConfiguration` constant in its subsystem's constants file.
- Name: `<MOTOR_DESCRIPTIVE_NAME>_CONFIGURATION` (e.g. `FLYWHEEL_WEST_CONFIGURATION`).
- Declare the configuration object at the top of the class.
- All `.apply()` calls must happen in a `static {}` initializer block — never inline in the subsystem constructor.

```java
// ✔ Good
public class ConstFreeSpin {
    public static final TalonFXConfiguration FLYWHEEL_WEST_CONFIGURATION = new TalonFXConfiguration();

    static {
        FLYWHEEL_WEST_CONFIGURATION.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        FLYWHEEL_WEST_CONFIGURATION.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    }
}
```

## Grouping with Inner Classes

- Use nested `public static class` blocks to group related constants (e.g. hardware variants, tuning sets).


## Naming Rules

- Constant names follow `PURPOSE_DESCRIPTION` — what it is used for + minimum detail to remove ambiguity.
- Do **not** repeat the subsystem name inside a constant's name — it is already implied by the class context.
- Valid examples: `FLYWHEEL_CORNER_SPEED`, `INTAKE_ROLLER_PERCENT_OUTPUT`, `OUTTAKE_TOLERANCE`, `CURRENT_LIMIT_FLOOR`.
- Avoid: `FREESPIN_FLYWHEEL_CORNER_SPEED` (subsystem name is redundant).

## Unit Conversions

- Never hardcode a pre-computed conversion result. Derive it from other constants using `Units`:

```java
// ❌ Bad
public static final double WHEEL_CIRCUMFERENCE = 0.31742888;

// ✔ Good
public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER.in(Units.Meters) * Math.PI;
```

## Common Review Flags

- Any raw numeric literal in a subsystem or command that should be in constants.
- Missing `TalonFXConfiguration` for any motor that exists in `DeviceIDs`.
- Physical quantity stored as `double` instead of a WPILib typed measure.
- Configuration logic placed in the subsystem constructor rather than a `static {}` block here.
