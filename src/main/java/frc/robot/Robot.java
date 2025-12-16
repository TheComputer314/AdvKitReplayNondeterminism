// Copyright (c) 2024 FRC 167
// https://github.com/icrobotics-team167
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;

public class Robot extends TimedRobot {
  public Robot() {
    DataLogManager.start();
    var log = DataLogManager.getLog();
    DriverStation.startDataLog(log);
  }

  @Override
  public void robotPeriodic() {
    DriverStationSim.setJoystickButton(0, 1, true);
    DriverStationSim.notifyNewData();
  }
}
