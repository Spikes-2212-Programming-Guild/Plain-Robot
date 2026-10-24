package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.smartmotorcontrollersubsystem.SmartMotorControllerGenericSubsystem;
import com.spikes2212.util.smartmotorcontrollers.TalonFXWrapper;
import frc.robot.RobotMap;

public class Shooter extends SmartMotorControllerGenericSubsystem {

    private static Shooter instance;

    private final TalonFXWrapper motor;

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter(new TalonFXWrapper(RobotMap.CAN.SHOOTER_MOTOR_PORT));
        }
        return instance;
    }

    public Shooter(TalonFXWrapper motor) {
        super("shooter", motor);
        this.motor = motor;
        motor.setInverted(true);
        configureDashboard();
    }

    @Override
    public void configureDashboard() {
        namespace.putNumber("velocity", motor::getVelocity);
    }
}
