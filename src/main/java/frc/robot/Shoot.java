package frc.robot;

import com.spikes2212.command.genericsubsystem.commands.smartmotorcontrollergenericsubsystem.MoveSmartMotorControllerGenericSubsystem;
import com.spikes2212.control.FeedForwardSettings;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.RootNamespace;
import com.spikes2212.util.UnifiedControlMode;

import java.util.function.Supplier;

public class Shoot extends MoveSmartMotorControllerGenericSubsystem {
    private static final Shooter shooter = Shooter.getInstance();
    private static final RootNamespace namespace = new RootNamespace("shoot");
    private static final PIDSettings settingsPID = namespace.addPIDNamespace("settings");
    private static final FeedForwardSettings settingFF = namespace.addFeedForwardNamespace("settings ff", FeedForwardSettings.EMPTY_FF_SETTINGS);
    private static final Supplier<Double> setpoint = namespace.addConstantDouble("setpoint", 0.0);

    public Shoot() {
        super(shooter, settingsPID, settingFF, UnifiedControlMode.VELOCITY, setpoint, true);
    }

}
