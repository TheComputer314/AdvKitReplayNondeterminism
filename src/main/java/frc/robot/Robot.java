// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;

import java.util.concurrent.ThreadLocalRandom;

public class Robot extends LoggedRobot {
    private static final Mode mode = Mode.REPLAY;
    public enum Mode {
        REAL,
        SIM,
        REPLAY
    }

    @SuppressWarnings("FieldCanBeLocal")
    private final CommandXboxController controller = new CommandXboxController(0);

    private int count = 0;

    public Robot() {
        if (isReal() || mode != Mode.REPLAY) {
            Logger.addDataReceiver(new WPILOGWriter()); // Log to a USB stick ("/U/logs")
            Logger.addDataReceiver(new NT4Publisher()); // Publish data to NetworkTables
        } else {
            setUseTiming(false); // Run as fast as possible
            String logPath = LogFileUtil.findReplayLog(); // Pull the replay log from AdvantageScope (or prompt the user)
            Logger.setReplaySource(new WPILOGReader(logPath)); // Read replay log
            Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_replay"))); // Save outputs to a new log

            DriverStation.silenceJoystickConnectionWarning(true);
        }

        Logger.start(); // Start logging! No more data receivers, replay sources, or metadata values may be added.

        controller.a()
                .whileTrue(new FunctionalCommand(() -> System.out.println("Started Counter"),
                  () -> {
                  Logger.recordOutput("CommandTimestamp", Timer.getTimestamp());
                  count++;
                }, (interrupted) -> System.out.println("Stopped Counter"), () -> false).ignoringDisable(true));
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

        Logger.recordOutput("PeriodicTimestamp", Timer.getTimestamp());
        Logger.recordOutput("TestCommandCount", count);
        Logger.recordOutput("ButtonHeld", controller.getHID().getAButton());
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void disabledExit() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void autonomousExit() {
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void teleopExit() {
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }

    @Override
    public void testExit() {
    }
}
