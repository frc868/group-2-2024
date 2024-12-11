// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.*;
import frc.robot.Constants.Auton;
import frc.robot.Constants.Controller;;

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
  boolean driveMode = true; //true for arcade, false for tank
  XboxController controller = new XboxController(0);


  @Override
  public void robotInit() {
    drivetrain = new Drivetrain();
    arm = new Arm();
    
    drivetrain.yawTare();
    drivetrain.resetEncoders();
    arm.resetEncoders();  
    drivetrain.setTargetAngle(drivetrain.getAngle());
  }


  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    autonNum = 0;
    drivetrain.yawTare();
    drivetrain.resetEncoders();
    arm.resetEncoders();  
  }

  @Override
  public void autonomousPeriodic(){ 
    switch (autonNum){
      case 0:
        drivetrain.setTargetAngle(drivetrain.getAngle());
        autonNum++;
        break;
        //Ensures forward driving
      case 1:
        drivetrain.rotateToAngle(Auton.Drive_0.SPEED);
        if (drivetrain.getAveragePosition() >= Auton.Drive_0.DISTANCE){
          autonNum++;
          drivetrain.stop();
        }
        break;
        //drives forward until encoder pos reaches required dist
      case 2:
        drivetrain.setTargetAngle(Auton.Turn_1.ANGLE);
        autonNum++;
        break;
        //sets turn angle
      case 3:
        drivetrain.rotateToAngle();
        if(drivetrain.atTarget()){
          autonNum++;
          drivetrain.stop();
        }
        break;
        //turns on the spot
      case 4:
        drivetrain.setTargetAngle(drivetrain.getAngle());
        autonNum++;
        drivetrain.resetEncoders();
        break;
        //ensures forward driving
      case 5:
        drivetrain.rotateToAngle(Auton.Drive_2.SPEED);
        if (drivetrain.getAveragePosition() >= Auton.Drive_2.DISTANCE){
          autonNum++;
          drivetrain.stop();
          drivetrain.resetEncoders();
        }
        break;
        //drives forward until encoder pos reaches required dist
      case 6:
        arm.setArmTarget(1);
        autonNum++;
        break;
        //set arm target
      case 7:
        //arm.rotateArm();
        if (arm.atTarget()){
          autonNum++;
        }
        break;
        //raise arm
      case 8:
        //arm.rotateArm();
        arm.startShooter();
        if (arm.getShooterPosition() >= 200){
          autonNum++;
          arm.stopShooter();
        }
        break;
        //shoot
      case 9:
        arm.setArmTarget(1);
        //arm.rotateArm();
        break; 
        //stop
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    if (controller.getStartButtonPressed()){
      driveMode = true;
    }
    if (controller.getBackButtonPressed()){
      driveMode = false;
    }
    /*if (controller.getYButtonPressed()){
      arm.setArmTarget(1);
    }else if (controller.getAButtonPressed()){
      arm.setArmTarget(2);
    }*/

    if (controller.getAButton()){
      drivetrain.rotateToAngle();
    }
    
    if (controller.getXButtonPressed()){
      drivetrain.yawTare();
      drivetrain.resetEncoders();
      drivetrain.setTargetAngle(drivetrain.getAngle());
    }
    
    if (controller.getYButtonPressed()){
      drivetrain.setTargetAngle(90);
    }

    

    // if (driveMode){
    //   drivetrain.arcadeDrive(controller.getLeftY(), controller.getRightX());
    // }else{  
    //   drivetrain.tankDrive(controller.getLeftY(), controller.getRightY());
    // }

    if (controller.getRightBumper()){
      arm.startShooter(1.0);
    }else if (controller.getLeftBumper()){
      arm.startShooter(-1.0);
    }else{
      arm.stopShooter();
    }

    // if (controller.getXButton()){
    //   arm.startIntake(-1.0);
    // }else if (controller.getBButton()){
    //   arm.startIntake(1.0);
    // }else{
    //   arm.stopIntake();
    // }

    if (controller.getLeftTriggerAxis() > Controller.threshholds.triggerDeadzone){
      arm.startArm(controller.getLeftTriggerAxis());
    }else if (controller.getRightTriggerAxis() > Controller.threshholds.triggerDeadzone){
      arm.startArm(-controller.getRightTriggerAxis());
    }else{
      arm.stopArm();
    }

    //arm.rotateArm();
      
    
  }

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
