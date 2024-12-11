package frc.robot;

import edu.wpi.first.math.util.Units;

public class Constants {
    public static final class Controller{
        public static final class threshholds{
            public static final double triggerDeadzone = 0.1;
        }
    }

    public static final class Drivetrain{
        
        public static class Limiter {
            public static final double SPEED_MULTIPLIER = 0.8;
            public static final double ROTATION_MULTIPLIER = 0.8;
        }

        public static final class Motors{

            public static final class CAN_IDs{
                public static final int LEFT_MOTOR = 7; //confirmed
                public static final int RIGHT_MOTOR = 8;
            }

            //TODO: UPDATE ENCODER CONVERSIONS AND DIRECTION INVERTS

            public static final class Direction{
                public static final boolean LEFT_REVERSED = true;
                public static final boolean RIGHT_REVERSED = false;
            }

            public static final class Encoders{
                public static final double ENCODER_TO_INCHES = 10.0;
            }
        }


        public static final class Turn_Controller{
            public static final double kP = 2.0;
            public static final double kI = 0.0;
            public static final double kD = 0.2;
            public static final double kToleranceDegrees = 2.0;
            public static final double MAX_VELOCITY = 5.0;
            public static final double MAX_ACCELERATION = 10.0;
            public static final double MULTIPLIER = 0.5;
            public static final double CLAMP = 0.5;
        }

    }

    public static final class Arm{
        public static final class Motors{
            public static final class CAN_IDs{
                public static final int ARM_MOTOR = 10; //confirmed
                public static final int INTAKE_MOTOR = 6; 
                public static final int SHOOTER_MOTOR = 9;
            }

            public static final class Direction{
                public static final boolean ARM_REVERSED = false;
                public static final boolean INTAKE_REVERSED = true;
                public static final boolean SHOOTER_REVERSED = false;
            }

            public static final class Voltages{
                public static final double ARM_VOLTAGE = 1.5;
                public static final double INTAKE_VOLTAGE = 1.5;
                public static final double SHOOTER_VOLTAGE = 9.0;
            }
        }

        public static final class Arm_Controller{
            public static final double kP = 6.0;
            public static final double kI = 0.0;
            public static final double kD = 0.3;

            public static final double kG = 0.5;
            public static final double kS = 0.7;
            public static final double kV = 0.0;
            public static final double kA = 0.0;

            public static final double kTolerance = Units.degreesToRadians(2.0);
            public static final double MAX_VELOCITY = 5.0;
            public static final double MAX_ACCELERATION = 10.0;
        }

        public static final class Arm_Targets{
            public static final double INTAKE_ARM = Units.degreesToRadians(-70.0);
            public static final double SHOOT_ARM = Units.degreesToRadians(-25.0);
        }

        public static final class Encoders{
            public static final double GEAR_RATIO = 1 / 20.0;
            public static final double ENCODER_TO_RADIANS = GEAR_RATIO * Math.PI * 2;
        }
    }
    
    public static final class Auton{
        public static final class Drive_0{
            public static final double DISTANCE = 10.0;
            public static final double SPEED = 0.5;
        }
        public static final class Turn_1{
            public static final double ANGLE = 45.0;
        }
        public static final class Drive_2{
            public static final double DISTANCE = 5.0;
            public static final double SPEED = 0.5;
        }
        public static final class RaiseArm_3{
            public static final int SETTING = 1;
        }
        public static final class Shoot_4{
            public static final int SETTING = 1;
        }
    }
    
}
