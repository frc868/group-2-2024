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
import edu.wpi.first.wpilibj2.TimedRobot;
import edu.wpi.first.wpilibj.SPI;

import edu.wpi.first.math.util.Units;

import frc.robot.Constants;


public class Drivetrain {
        /*
         * Define and initialize the left and right motor controllers with the names `leftMotor` and
         * `rightMotor` respectively
         */

        private CANSparkMax leftMotor = new CANSparkMax(
                        Constants.Drivetrain.Motor.CAN_IDs.LEFT_MOTOR, MotorType.kBrushless);

        private CANSparkMax rightMotor = new CANSparkMax(
                        Constants.Drivetrain.Motor.CAN_IDs.RIGHT_MOTOR, MotorType.kBrushless);

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
                leftMotor.setInverted(Constants.Drivetrain.Motor.Direction.LEFT_INVERTED);
                rightMotor.setInverted(Constants.Drivetrain.Motor.Direction.RIGHT_INVERTED);

                /*
                 * Set the position reading reported by encoders to be in inches instead of encoder
                 * units
                 */
                leftEncoder.setPositionConversionFactor(
                                Constants.Drivetrain.Encoders.ENCODER_TO_INCHES);
                rightEncoder.setPositionConversionFactor(
                                Constants.Drivetrain.Encoders.ENCODER_TO_INCHES);

        }   


}