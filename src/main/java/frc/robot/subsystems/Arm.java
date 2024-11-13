package main.java.frc.robot.subsystems;
import frc.robot.Constants;

public class Arm {
        private CANSparkMax armMotor = new CANSparkMax(
                        Constants.Drivetrain.Motor.CAN_IDs.LEFT_MOTOR, MotorType.kBrushless);
        private CANSparkMax intakeMotor = new CANSparkMax(
                        Constants.Drivetrain.Motor.CAN_IDs.RIGHT_MOTOR, MotorType.kBrushless);
        private CANSparkMax shooterMotor = new CANSparkMax(
                        Constants.Drivetrain.Motor.CAN_IDs.RIGHT_MOTOR, MotorType.kBrushless);

        /*
         * Define and initialize arm, intake, and shooter encoders. 
         */
        private RelativeEncoder armEncoder = armMotor.getEncoder();
        private RelativeEncoder intakeEncoder = intakeMotor.getEncoder();
        private RelativeEncoder shooterEncoder = shooterMotor.getEncoder();
    
}
