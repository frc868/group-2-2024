package frc.robot.subsystems;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.Constants.Arm.Arm_Controller;
import frc.robot.Constants.Arm.Motors;
import frc.robot.Constants.Arm.Arm_Targets;;


public class Arm {
        private CANSparkMax armMotor = new CANSparkMax(
                        Motors.CAN_IDs.ARM_MOTOR, MotorType.kBrushless);
        private CANSparkMax intakeMotor = new CANSparkMax(
                        Motors.CAN_IDs.INTAKE_MOTOR, MotorType.kBrushless);
        private CANSparkMax shooterMotor = new CANSparkMax(
                        Motors.CAN_IDs.SHOOTER_MOTOR, MotorType.kBrushless);

        /*
         * Define and initialize arm, intake, and shooter encoders. 
         */
        private RelativeEncoder armEncoder = armMotor.getEncoder();
        private RelativeEncoder intakeEncoder = intakeMotor.getEncoder();
        private RelativeEncoder shooterEncoder = shooterMotor.getEncoder();
        private double rotateArmRate;

        private ProfiledPIDController armController;

        public Arm(){
                armMotor.setInverted(Motors.Direction.ARM_REVERSED);
                intakeMotor.setInverted(Motors.Direction.INTAKE_REVERSED);
                shooterMotor.setInverted(Motors.Direction.SHOOTER_REVERSED);
                armController = new ProfiledPIDController(Arm_Controller.kP, Arm_Controller.kI, Arm_Controller.kD, new TrapezoidProfile.Constraints(Arm_Controller.MAX_VELOCITY, Arm_Controller.MAX_ACCELERATION));
        }

        public void startIntake(){
                intakeMotor.setVoltage(Motors.Voltages.INTAKE_VOLTAGE);
        }

        public void stopIntake(){
                intakeMotor.stopMotor();
        }

        public void startShooter(){
                shooterMotor.setVoltage(Motors.Voltages.SHOOTER_VOLTAGE);
        }

        public void stopShooter(){
                shooterMotor.stopMotor();
        }

        public void setArmTarget(int target){
                switch (target){
                        case 1:
                                armController.setGoal(Arm_Targets.HIGH_ARM);
                                break;
                        case 2:
                                armController.setGoal(Arm_Targets.MID_ARM);
                                break;
                        case 3:
                                armController.setGoal(Arm_Targets.LOW_ARM);
                                break;
                        case 4:
                                armController.setGoal(Arm_Targets.FLOOR_ARM);
                                break;
                }       
        }
}
