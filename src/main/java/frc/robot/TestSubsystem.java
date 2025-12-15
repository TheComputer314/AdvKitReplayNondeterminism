package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class TestSubsystem extends SubsystemBase {
    private int counter = 0;
    private boolean counterStarted = false;

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
      if (counterStarted) {
        counter++;
      }
        Logger.recordOutput("TestSubsystemCounter",counter);
    }

    public void startCounter() {
        counterStarted = true;
    }

    public void stopCounter() {
        counterStarted = false;
    }
}
