// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

    SparkMax spark1 = new SparkMax(1, SparkLowLevel.MotorType.kBrushless);
    SparkMax spark2 = new SparkMax(4, SparkLowLevel.MotorType.kBrushless);
    @Override
    public void robotInit() {

    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        CommandScheduler.getInstance().cancelAll();
        spark1.stopMotor();
        spark2.stopMotor();
        spark1.configure(new SparkMaxConfig().idleMode(SparkBaseConfig.IdleMode.kBrake), SparkBase.ResetMode.kNoResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
        spark2.configure(new SparkMaxConfig().idleMode(SparkBaseConfig.IdleMode.kBrake), SparkBase.ResetMode.kNoResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
    }

    @Override
    public void disabledPeriodic() {

    }

    @Override
    public void autonomousInit() {
        spark1.set(-0.2);
        spark2.set(-0.2);
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {
        spark1.set(0.2);
        spark2.set(0.2);
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
