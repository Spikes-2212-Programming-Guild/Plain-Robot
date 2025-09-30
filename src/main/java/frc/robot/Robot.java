// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.RootNamespace;
import commands.RunPID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.function.Supplier;

public class Robot extends TimedRobot {

    private final RootNamespace namespace = new RootNamespace("speeds");
    private final TalonFX motor = new TalonFX(RobotMap.CAN.TALON_ID);

    private Supplier<Double> kP, kI, kD, Izone, tolorence, waitTime;
    private PIDSettings settings;

    @Override
    public void robotInit() {
        Supplier<Double> speed1 = namespace.addConstantDouble("speed1", 0.5);
        Supplier<Double> speed2 = namespace.addConstantDouble("speed2", 0.7);

        namespace.putRunnable("run speed 1", () -> motor.set(speed1.get()));
        namespace.putRunnable("run speed 2", () -> motor.set(speed2.get()));
        kP = namespace.addConstantDouble("proportional gain", -1);
        kI = namespace.addConstantDouble("integral gain", -1);
        kD = namespace.addConstantDouble("derative gain", -1);
        Izone = namespace.addConstantDouble("iZone", -1);
        tolorence = namespace.addConstantDouble("tolorence", -1);
        waitTime = namespace.addConstantDouble("wait time", 3.0);

        settings = new PIDSettings(kP, kI, kD, Izone, tolorence, waitTime);

        RunPID commandSpeed1 = new RunPID(settings, motor, speed1);
        RunPID commandSpeed2 = new RunPID(settings, motor, speed2);

        namespace.putCommand("run pid speed1", commandSpeed1);
        namespace.putCommand("run pid speed2", commandSpeed2);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        namespace.update();
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void autonomousInit() {

    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {

    }

    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {

    }

    @Override
    public void simulationInit() {

    }

    @Override
    public void simulationPeriodic() {

    }
}
