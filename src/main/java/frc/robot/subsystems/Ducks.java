package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class Ducks extends GenericSubsystem {

    public final SparkMax spark = new SparkMax(RobotMap.CAN.SPARK, SparkLowLevel.MotorType.kBrushless);
    public final DigitalInput leftLimit = new DigitalInput(RobotMap.DIO.LEFT);
    public final DigitalInput rightLimit = new DigitalInput(RobotMap.DIO.RIGHT);

    private static Ducks instance;

    public static Ducks getInstance() {
        if (instance == null) {
            instance = new Ducks();
        }
        return instance;
    }

    public Ducks() {
        super("ducks");
    }

    @Override
    public void apply(double speed) {
        spark.set(speed);
    }

    @Override
    public boolean canMove(double speed) {
        return (leftLimit.get() && speed < 0) || (rightLimit.get() && speed > 0);
    }

    @Override
    public void stop() {
        spark.stopMotor();
    }

    @Override
    public void configureDashboard() {

    }
}
