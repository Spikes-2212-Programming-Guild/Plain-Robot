package frc.robot.commands;

import com.spikes2212.command.genericsubsystem.commands.smartmotorcontrollergenericsubsystem.MoveSmartMotorControllerGenericSubsystem;
import com.spikes2212.control.FeedForwardSettings;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.RootNamespace;
import com.spikes2212.util.UnifiedControlMode;
import frc.robot.subsystems.Shooter;

import java.util.function.Supplier;

public class SmartShoot extends MoveSmartMotorControllerGenericSubsystem {

    private static final RootNamespace NAMESPACE = new RootNamespace("smart shoot");
    private static final PIDSettings PID_SETTINGS =
            NAMESPACE.addPIDNamespace("", PIDSettings.EMPTY_PID_SETTINGS);
    private static final FeedForwardSettings FEED_FORWARD_SETTINGS =
            NAMESPACE.addFeedForwardNamespace("", FeedForwardSettings.EMPTY_FF_SETTINGS);

    public SmartShoot(Supplier<Double> setpoint) {
        super(Shooter.getInstance(), PID_SETTINGS, FEED_FORWARD_SETTINGS,
                UnifiedControlMode.VELOCITY, setpoint, true);
    }

    public static void updateNamespace() {
        NAMESPACE.update();
    }
}
