package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Ducks;

import java.util.function.Supplier;

public class MoveDucksMove extends Command {

    public final Ducks ducks = Ducks.getInstance();
    public final double speed;

    public MoveDucksMove(double speed) {
        addRequirements();
        this.speed = speed;
    }

    @Override
    public void execute() {
        ducks.apply(speed);
    }

    @Override
    public boolean isFinished() {
        return ducks.canMove(speed);
    }

    @Override
    public void end(boolean interrupted) {
        ducks.stop();
        if (!interrupted) new MoveDucksMove(-speed).schedule();
    }
}
