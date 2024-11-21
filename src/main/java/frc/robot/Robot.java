// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.*;
import frc.robot.Constants.Auton;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  Drivetrain drivetrain;
  Arm arm;
  int autonNum;


  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();
    arm = new Arm();
  }


  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    autonNum = 0;
    drivetrain.yawTare();
    drivetrain.resetEncoders();
  }

  @Override
  public void autonomousPeriodic(){ 
    switch (autonNum){
      case 0:
        drivetrain.setTargetAngle(drivetrain.getAngle());
        autonNum++;
        //Ensures forward driving
      case 1:
        drivetrain.rotateToAngle(Auton.Drive_0.SPEED);
        if (drivetrain.getAveragePosition() >= Auton.Drive_0.DISTANCE){
          autonNum++;
          drivetrain.stop();
        }
        //drives forward until encoder pos reaches required dist
      case 2:
        drivetrain.setTargetAngle(Auton.Turn_1.ANGLE);
        autonNum++;
        //sets turn angle
      case 3:
        drivetrain.rotateToAngle();
        if(drivetrain.atTarget()){
          autonNum++;
          drivetrain.stop();
        }
        //turns on the spot
      case 4:
        drivetrain.setTargetAngle(drivetrain.getAngle());
        autonNum++;
        drivetrain.resetEncoders();
        //ensures forward driving
      case 5:
        drivetrain.rotateToAngle(Auton.Drive_2.SPEED);
        if (drivetrain.getAveragePosition() >= Auton.Drive_2.DISTANCE){
          autonNum++;
          drivetrain.stop();
          drivetrain.resetEncoders();
        }
        //drives forward until encoder pos reaches required dist
      case 6:
        //set arm target
      case 7:
        //raise arm
      case 8:
        //shoot
      case 9:
        //stop
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
