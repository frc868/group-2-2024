package frc.robot;

public class Constants {
    public static final class Drivetrain{

        public static final class Motors{
            public static final class CAN_IDs{
                public static final int LEFT_MOTOR = 1;
                public static final int RIGHT_MOTOR = 2;
            }

            //TODO: UPDATE ENCODER CONVERSIONS AND DIRECTION INVERTS

            public static final class Direction{
                public static final boolean LEFT_REVERSED = false;
                public static final boolean RIGHT_REVERSED = false;
            }

            public static final class Encoders{
                public static final double ENCODER_TO_INCHES = 1.0;
            }
        }


        public static final class Turn_Controller{
            public static final double kP = 1.0;
            public static final double kI = 0.1;
            public static final double kD = 0.0;
            public static final double kF = 0.0;
            public static final double kToleranceDegrees = 2.0;
            public static final double MAX_VELOCITY = 5.0;
            public static final double MAX_ACCELERATION = 10.0;
        }
    }

    public static final class Arm{
        
        public static final class Motors{
            public static final class CAN_IDs{
                public static final int ARM_MOTOR = 3;
                public static final int INTAKE_MOTOR = 4;
                public static final int SHOOTER_MOTOR = 5;
            }
        }
    }
    
}
