// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Map;
import java.util.Set;

import com.frcteam3255.joystick.SN_XboxController;

import choreo.auto.AutoFactory;
import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.epilogue.NotLogged;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.DeferredCommand;
import frc.robot.DeviceIDs.controllerIDs;
import frc.robot.commands.AddVisionMeasurement;
import frc.robot.commands.ResetPose;
import frc.robot.constants.ChoreoTraj;
import frc.robot.constants.ConstSystem;
import frc.robot.constants.ConstSystem.constControllers;
import frc.robot.subsystems.DriverStateMachine;
import frc.robot.subsystems.DriverStateMachine.DriverState;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.FreeSpin;
import frc.robot.subsystems.Positional;
import frc.robot.subsystems.RobotPoses;
import frc.robot.subsystems.StateMachine;
import frc.robot.subsystems.StateMachine.RobotState;
import frc.robot.subsystems.Telemetry;
import frc.robot.subsystems.Vision;

@Logged
public class RobotContainer {
  @NotLogged
  SendableChooser<Command> autoChooser = new SendableChooser<>();
  public String pathString = "";
  public Pose2d pathStartPose = new Pose2d();
  public Pose2d pathEndPose = new Pose2d();
  private AutoFactory autoFactory;

  private final SN_XboxController conDriver = new SN_XboxController(controllerIDs.DRIVER_USB);

  public static final FreeSpin freeSpinInstance = new FreeSpin();
  private final FreeSpin loggedFreeSpinInstance = freeSpinInstance;
  public static final Positional positionalInstance = new Positional();
  private final Positional loggedPositionalInstance = positionalInstance;
  public static final Drivetrain drivetrainInstance = new Drivetrain();
  private final Drivetrain loggedDrivetrainInstance = drivetrainInstance;
  public static final DriverStateMachine driverStateMachineInstance = new DriverStateMachine();
  private final DriverStateMachine loggedDriverStateMachineInstance = driverStateMachineInstance;
  public static final StateMachine stateMachineInstance = new StateMachine();
  private final StateMachine loggedStateMachineInstance = stateMachineInstance;
  public static final RobotPoses robotPose = new RobotPoses();
  private final RobotPoses loggedRobotPose = robotPose;
  public static final Vision visionInstance = new Vision();
  private final Vision loggedVisionInstance = visionInstance;
  public static final Telemetry telemetryInstance = new Telemetry();
  private final Telemetry loggedTelemetryInstance = telemetryInstance;

  Command TRY_NONE = Commands.deferredProxy(
      () -> stateMachineInstance.tryState(RobotState.NONE));

  Command MANUAL = new DeferredCommand(
      driverStateMachineInstance.tryState(
          DriverStateMachine.DriverState.MANUAL,
          conDriver.axis_LeftY,
          conDriver.axis_LeftX,
          conDriver.axis_RightX,
          conDriver.btn_RightBumper),
      Set.of(driverStateMachineInstance));

  Command EXAMPLE_POSE_DRIVE = new DeferredCommand(
      driverStateMachineInstance.tryState(
          DriverStateMachine.DriverState.EXAMPLE_POSE_DRIVE,
          conDriver.axis_LeftY,
          conDriver.axis_LeftX,
          conDriver.axis_RightX,
          conDriver.btn_RightBumper),
      Set.of(driverStateMachineInstance));

  public RobotContainer() {
    conDriver.setLeftDeadband(constControllers.DRIVER_LEFT_STICK_DEADBAND);

    driverStateMachineInstance
        .setDefaultCommand(MANUAL);

    configDriverBindings();
    configOperatorBindings();
    configAutonomous();
    RobotController.setBrownoutVoltage(5.5);
    // drivetrainInstance.resetModulesToAbsolute();
  }

  private void configDriverBindings() {
    conDriver.btn_North.whileTrue(new ResetPose());
    // Example Pose Drive
    conDriver.btn_X
        .whileTrue(EXAMPLE_POSE_DRIVE)
        .onFalse(Commands.runOnce(() -> driverStateMachineInstance.setDriverState(DriverState.MANUAL)));
  }

  private void configOperatorBindings() {
    // Add operator bindings here if needed
  }

  public void configAutonomous() {
    autoFactory = new AutoFactory(
        drivetrainInstance::getPose, // A function that returns the current robot pose
        drivetrainInstance::resetPose, // A function that resets the current robot pose to the provided Pose2d
        drivetrainInstance::followTrajectory, // The drive subsystem trajectory follower
        true, // If alliance flipping should be enabled
        driverStateMachineInstance // The drive subsystem
    );

    Command AutoPIDTuning = Commands.sequence(runPath(ChoreoTraj.AutoPIDTuning));
    Command DoNothing = Commands.none();

    // Example: Add autonomous routines to the chooser
    // Add more autonomous routines as needed, e.g.:
    // autoChooser.addOption("Score and Leave", runPath("ScoreAndLeave"));
    autoChooser.setDefaultOption("Do Nothing", DoNothing);
    autoChooser.addOption("AutoPIDTuning", AutoPIDTuning);

    // make our entries name
    final Map<Command, ChoreoTraj> autoStartingPoses = Map.ofEntries(
        // Example
        // TODO: update DoNotiong Path match your actual field
        Map.entry(DoNothing, ChoreoTraj.AutoPIDTuning),
        Map.entry(AutoPIDTuning, ChoreoTraj.AutoPIDTuning));

    // enter which we want to do based on name
    autoChooser.onChange(selectedAuto ->

    {
      ChoreoTraj startingPose = autoStartingPoses.get(selectedAuto);
      // if there is a starting pose, reset to it
      if (startingPose != null) { // Run even when disabled
        CommandScheduler.getInstance().schedule(autoFactory.resetOdometry(startingPose.name())
            .ignoringDisable(true));// Run even when disabled
      }
    });

    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  public Command runPath(ChoreoTraj path) {
    return autoFactory.trajectoryCmd(path.name()).asProxy()
        .alongWith(Commands.runOnce(() -> {
          pathString = path.name();
          pathStartPose = path.initialPoseBlue();
          pathEndPose = path.endPoseBlue();
          driverStateMachineInstance.setDriverState(DriverState.CHOREO);
        }));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }

  public RobotState getRobotState() {
    return stateMachineInstance.getRobotState();
  }

  public String robotStateToString() {
    return stateMachineInstance.getRobotState().toString();
  }

  public String driverStateToString() {
    return driverStateMachineInstance.getDriverState().toString();
  }

  public Command addVisionMeasurement() {
    return new AddVisionMeasurement()
        .withInterruptBehavior(Command.InterruptionBehavior.kCancelIncoming).ignoringDisable(true);
  }

  public static boolean isPracticeBot() {
    return RobotController.getSerialNumber().equals(ConstSystem.PRACTICE_BOT_RIO_SERIAL_NUMBER);
  }
}
