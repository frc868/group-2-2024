package frc.robot.subsystems;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import frc.robot.Constants.Arm.Arm_Controller;
import frc.robot.Constants.Arm.Arm_Targets;
import frc.robot.Constants.Arm.Motors;
import frc.robot.Constants.Arm.Motors.Voltages;

import frc.robot.Constants.Arm.Encoders;

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
        private ArmFeedforward armFeedforward;

        public Arm(){
                armMotor.setSmartCurrentLimit(40);
                shooterMotor.setSmartCurrentLimit(55);
                intakeMotor.setSmartCurrentLimit(40);
                armMotor.setInverted(Motors.Direction.ARM_REVERSED);
                intakeMotor.setInverted(Motors.Direction.INTAKE_REVERSED);
                shooterMotor.setInverted(Motors.Direction.SHOOTER_REVERSED);
                armEncoder.setPositionConversionFactor(Encoders.ENCODER_TO_RADIANS);
                armEncoder.setPosition(0);
                armController = new ProfiledPIDController(Arm_Controller.kP, Arm_Controller.kI, Arm_Controller.kD, new TrapezoidProfile.Constraints(Arm_Controller.MAX_VELOCITY, Arm_Controller.MAX_ACCELERATION));
                armFeedforward = new ArmFeedforward(Arm_Controller.kS, Arm_Controller.kG, Arm_Controller.kV, Arm_Controller.kA);
        }

        public void startIntake(double speed){
                intakeMotor.setVoltage(Motors.Voltages.INTAKE_VOLTAGE * speed);
        }

        public void startIntake(){
                startIntake(1.0);
        }

        public void stopIntake(){
                intakeMotor.stopMotor();
        }

        public void startShooter(double speed){
                shooterMotor.setVoltage(Motors.Voltages.SHOOTER_VOLTAGE * speed);
        }

        public void startShooter(){
                startShooter(1.0);
        }

        public void stopShooter(){
                shooterMotor.stopMotor();
        }

        public void setArmTarget(int target){
                //armController.reset(getArmPosition());
                switch (target){
                        case 1:
                                armController.setGoal(Arm_Targets.SHOOT_ARM);
                                break;
                        case 2:
                                armController.setGoal(Arm_Targets.INTAKE_ARM);
                                break;
                }       
        }

        public double getArmPosition(){
                return armEncoder.getPosition();
        }

        public void rotateArm(){
                //armController.reset(getArmPosition());
                double feedforward = armFeedforward.calculate(armController.getSetpoint().position, armController.getSetpoint().velocity);
                double pid = armController.calculate(getArmPosition());
                rotateArmRate = MathUtil.clamp(pid /*+ feedforward*/, -Voltages.ARM_VOLTAGE, Voltages.ARM_VOLTAGE);
                armMotor.setVoltage(rotateArmRate);
        }

        public void stopArm(){
                armMotor.stopMotor();
        }

        public void startArm(double speed){
                armMotor.setVoltage(Voltages.ARM_VOLTAGE * speed);
        }

        public boolean atTarget(){
                return armController.atGoal();
        }


        public void resetEncoders(){
                armEncoder.setPosition(0);
                intakeEncoder.setPosition(0);
                shooterEncoder.setPosition(0);
        }

        public double getShooterPosition(){
                return shooterEncoder.getPosition();
        }
}
