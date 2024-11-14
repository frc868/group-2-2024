package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants;
import frc.robot.Constants.Arm.Motors;


public class Arm {
        @SuppressWarnings("removal")
        private CANSparkMax armMotor = new CANSparkMax(
                        Motors.CAN_IDs.ARM_MOTOR, MotorType.kBrushless);
        @SuppressWarnings("removal")
        private CANSparkMax intakeMotor = new CANSparkMax(
                        Motors.CAN_IDs.INTAKE_MOTOR, MotorType.kBrushless);
        @SuppressWarnings("removal")
        private CANSparkMax shooterMotor = new CANSparkMax(
                        Motors.CAN_IDs.SHOOTER_MOTOR, MotorType.kBrushless);

        /*
         * Define and initialize arm, intake, and shooter encoders. 
         */
        private RelativeEncoder armEncoder = armMotor.getEncoder();
        private RelativeEncoder intakeEncoder = intakeMotor.getEncoder();
        private RelativeEncoder shooterEncoder = shooterMotor.getEncoder();
}
