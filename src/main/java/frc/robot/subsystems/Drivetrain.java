package frc.robot.subsystems;


import edu.wpi.first.wpilibj.TimedRobot;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.MathUtil;


import frc.robot.Constants;
import frc.robot.Constants.Drivetrain.Motors;
import frc.robot.Constants.Drivetrain.Turn_Controller;
import frc.robot.Constants.Drivetrain.Motors.Encoders;


public class Drivetrain {
        private AHRS ahrs;
        private ProfiledPIDController turnController;

        private double targetAngle;
        private double rotateToAngleRate;

        /*
         * Define and initialize the left and right motor controllers with the names `leftMotor` and
         * `rightMotor` respectively
         */

        @SuppressWarnings("removal")
        private CANSparkMax leftMotor = new CANSparkMax(
                        Motors.CAN_IDs.LEFT_MOTOR, MotorType.kBrushless);

        @SuppressWarnings("removal")
        private CANSparkMax rightMotor = new CANSparkMax(
                        Motors.CAN_IDs.RIGHT_MOTOR, MotorType.kBrushless);

        private final SlewRateLimiter leftLimiter = new SlewRateLimiter(2);
        private final SlewRateLimiter rightLimiter = new SlewRateLimiter(2);

        /*
         * Define and initialize the left and right motor encoders with the names `leftEncoder` and
         * `rightEncoder` respectively
         */
        private RelativeEncoder leftEncoder = leftMotor.getEncoder();
        private RelativeEncoder rightEncoder = rightMotor.getEncoder();

        /* Define and initialize a DifferentialDrive called `drive` */
        private DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);

        /* Define and initialize the NAVX2 Gyroscope called 'gyro' */

        /* Initialize the drivetrain */
        public Drivetrain() {
                /* Set the motors to be inverted correctly */
                leftMotor.setInverted(Motors.Direction.LEFT_REVERSED);
                rightMotor.setInverted(Motors.Direction.RIGHT_REVERSED);

                /*
                 * Set the position reading reported by encoders to be in inches instead of encoder
                 * units
                 */
                leftEncoder.setPositionConversionFactor(
                               Motors.Encoders.ENCODER_TO_INCHES);
                rightEncoder.setPositionConversionFactor(
                                Motors.Encoders.ENCODER_TO_INCHES);

        }   

        /* Unassisted tankdrive */
        public void tankDrive(double left, double right){
            drive.tankDrive(left, right);   
        }

        /* Unassisted arcade drive */
        public void arcadeDrive(double fwd, double rot){
            drive.arcadeDrive(fwd, rot);
        }

        /* resets motor encoders */
        public void resetEncoders(){
            leftEncoder.setPosition(0);
            rightEncoder.setPosition(0);
        }

        public double getAveragePosition() {
            return (leftEncoder.getPosition() + rightEncoder.getPosition()) / 2;
        }

        /* Initializes NavX2, call at robotInit */
        public void ahrsInit(){
            try{
                ahrs = new AHRS(SPI.Port.kMXP);
            } catch (RuntimeException ex){
                DriverStation.reportError("Error instantiating navX MXP: " + ex.getMessage(), true);
            }
            turnController = new ProfiledPIDController(Turn_Controller.kP, Turn_Controller.kI, Turn_Controller.kD, new TrapezoidProfile.Constraints(Turn_Controller.MAX_VELOCITY, Turn_Controller.MAX_ACCELERATION));
            turnController.enableContinuousInput(-180.0, 180.0);
            turnController.setTolerance(Turn_Controller.kToleranceDegrees);
        }

        /* Resets the NavX2 yaw */
        public void yawTare(){
            ahrs.zeroYaw();
        }

        /* Sets turning target angle */
        public void setTargetAngle(double angle){
            targetAngle = angle;
        }

        /* Rotates to target angle with zero forward movement*/
        public void rotateToAngle(){
            rotateToAngle(0.0);
        }

        /* Rotates to target angle with forward movement */
        public void rotateToAngle(double fwd){
            rotateToAngleRate = 0.0;
            rotateToAngleRate = MathUtil.clamp(turnController.calculate(ahrs.getAngle(), targetAngle), -1.0, 1.0);
            drive.arcadeDrive(fwd, rotateToAngleRate);
        }

        /* Returns true if the robot is at the target angle */
        public boolean atTarget(){
            return turnController.atGoal();
        }


}