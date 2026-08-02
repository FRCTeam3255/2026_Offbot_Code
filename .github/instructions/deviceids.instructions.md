---
applyTo: "src/main/java/frc/robot/DeviceIDs.java"
---

# DeviceIDs.java Instructions

`DeviceIDs.java` is the **single source of truth** for all hardware port and ID mappings on the robot. Every motor, encoder, sensor, and controller ID lives here and nowhere else.

## File Structure

- One top-level `public class DeviceIDs` with no constructor.
- One nested `public static class` per subsystem, named in `lowerCamelCase` with an `IDs` suffix.
- A comment above each inner class indicating its CAN ID range.

```java
public class DeviceIDs {
    public static class controllerIDs { ... }   // USB ports

    // Drivetrain IDs: 0–9
    public static class drivetrainIDs { ... }

    // FreeSpin IDs: 10–29
    public static class freeSpinIDs { ... }

    // Positional IDs: 30–49
    public static class positionalIDs { ... }
}
```

## ID Constant Naming

- All ID constants are `public static final int` (or `CANBus` for the CAN bus name) and `SCREAMING_SNAKE_CASE`.
- CAN IDs: `MECHANISM_POSITION_CAN` (e.g. `INTAKE_ROLLERS_WEST_CAN`, `FLYWHEEL_WEST_CAN`).
- Non-CAN: `DEVICETYPE_LOCATION_CONNECTIONTYPE` (e.g. `ENCODER_FRONT_LEFT_DIO`, `DRIVER_USB`).
- Do **not** include the subsystem name in the constant name — the inner class already provides that context.

```java
// ❌ Bad — subsystem name is redundant inside freeSpinIDs
public static class freeSpinIDs {
    public static final int FREESPIN_INTAKE_ROLLERS_WEST_CAN = 10;
}

// ✔ Good
public static class freeSpinIDs {
    public static final int INTAKE_ROLLERS_WEST_CAN = 10;
}
```

## CAN ID Ranges

Maintain the established ID range blocks and keep IDs within their assigned ranges:
- `drivetrainIDs`: `0–9`
- `freeSpinIDs`: `10–29`
- `positionalIDs`: `30–49`

Flag any ID that falls outside its designated range.

## Every Motor Needs an ID

- Every `TalonFX` declared in `FreeSpin.java` or `Positional.java` must have a corresponding constant in `freeSpinIDs` or `positionalIDs` respectively.
- Every `TalonFX` in `Drivetrain.java` must have a corresponding constant in `drivetrainIDs`.
- Flag any motor instantiated with a raw integer literal instead of a reference to `DeviceIDs`.

## CANBus

- The swerve drivetrain CAN bus is declared as a `CANBus` object (not an `int`) in `drivetrainIDs`.
- Name: `CAN_BUS_NAME`.

## Common Review Flags

- New motor added to a subsystem but no corresponding ID added here.
- ID constant name repeats the subsystem name (redundant with the inner class).
- Raw integer literal used in a subsystem motor constructor instead of referencing `DeviceIDs`.
- ID value falls outside the designated range for its inner class.
- New subsystem added but no corresponding inner class created in `DeviceIDs`.
- Inner class name doesn't follow `lowerCamelCase` + `IDs` pattern.
