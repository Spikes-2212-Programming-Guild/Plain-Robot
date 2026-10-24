package frc.robot;

import com.spikes2212.command.genericsubsystem.smartmotorcontrollersubsystem.SmartMotorControllerGenericSubsystem;
import com.spikes2212.control.FeedForwardSettings;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.util.smartmotorcontrollers.TalonFXWrapper;

public class Shooter extends SmartMotorControllerGenericSubsystem {

    private static Shooter instance;

    private Shooter(){
        super("shooter", new TalonFXWrapper(RobotMap.CAN.TALON_ID));
        configureDashboard();
    }

    public static Shooter getInstance(){
        if(instance == null){
            instance = new Shooter();
        }
            return instance;
    }

    @Override
    public void configureDashboard() {
        namespace.putNumber("shooter speed", this::getSpeed);
    }

    public void updateNamespace(){
        namespace.update();
    }
}
