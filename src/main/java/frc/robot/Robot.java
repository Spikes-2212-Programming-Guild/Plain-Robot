// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.spikes2212.dashboard.RootNamespace;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.SmartShoot;
import frc.robot.commands.Shoot;

import java.util.function.Supplier;

public class Robot extends TimedRobot {

    private final RootNamespace namespace = new RootNamespace("robot");

    @Override
    public void robotInit() {
        Supplier<Double> supplier = namespace.addConstantDouble("setpoint", 0.0);
        namespace.putCommand("smart shoot", new SmartShoot(supplier));
        namespace.putCommand("shoot", new Shoot(namespace.addConstantDouble("speed", 0.0)));
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        SmartShoot.updateNamespace();
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
