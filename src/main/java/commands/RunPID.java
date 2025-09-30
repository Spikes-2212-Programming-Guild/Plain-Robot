package commands;

import com.ctre.phoenix6.hardware.TalonFX;
import com.spikes2212.control.PIDSettings;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

import java.util.function.Supplier;

public class RunPID extends Command {

    private static final double TICK_RATE = 0.02;

    private final PIDController pidController;
    private final PIDSettings pidSettings;
    private final TalonFX motor;
    private final Supplier<Double> setPoint;
    private double targetTime;

    public RunPID(PIDSettings pidSettings, TalonFX motor, Supplier<Double> setPoint) {
        this.pidSettings = pidSettings;
        this.motor = motor;
        this.setPoint = setPoint;
        pidController = new PIDController(0, 0, 0);
        ConfigPID();
    }

    public void ConfigPID() {
        pidController.setPID(pidSettings.getkP(), pidSettings.getkI(), pidSettings.getkD());
        pidController.setSetpoint(setPoint.get());
        pidController.setTolerance(pidSettings.getTolerance());
        pidController.setIZone(pidSettings.getIZone());
    }


    @Override
    public void execute() {
        double measurement = motor.getVelocity().getValueAsDouble();
        double output = pidController.calculate(measurement, setPoint.get());
        motor.set(output);
        if (pidController.atSetpoint()) {
            targetTime += TICK_RATE;
        } else {
            targetTime = 0;
        }
    }

    @Override
    public boolean isFinished() {
        return targetTime >= pidSettings.getWaitTime();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
    }
}

