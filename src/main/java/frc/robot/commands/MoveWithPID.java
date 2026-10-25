package frc.robot.commands;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.Orchestra;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Timer;

import java.util.function.Supplier;

public class MoveWithPID extends Command {

    Orchestra sound = new Orchestra("output.chrp");
    private final Timer timer = new Timer();

    private final TalonFX talon;

    private final Supplier<Double> goalSpeed;
    private final Supplier<Double> tolerance;

    private final PIDController pid;

    public MoveWithPID(TalonFX talon, Supplier<Double> kP, Supplier<Double> kI, Supplier<Double> kD,
                       Supplier<Double> goalSpeed, Supplier<Double> tolerance){
        this.talon = talon;
        this.goalSpeed = goalSpeed;
        this.tolerance = tolerance;
        pid = new PIDController(kP.get(),kI.get(),kD.get());
    }

    @Override
    public void execute() {
        pid.setTolerance(tolerance.get());
        talon.set(pid.calculate(talon.getVelocity().getValueAsDouble(), goalSpeed.get()));
        if (talon.getVelocity().getValueAsDouble() == goalSpeed.get()){
            timer.start();
        }
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= 3;
    }

    public void end(boolean interrupted) {
        talon.stopMotor();
        sound.play();
    }


}
