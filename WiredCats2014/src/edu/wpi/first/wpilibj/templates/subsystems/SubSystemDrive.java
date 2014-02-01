
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Accelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandArcadeDrive;
import edu.wpi.first.wpilibj.templates.commands.CommandTankDrive;

/**
 *
 */
public class SubSystemDrive extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public static final int TICKS_PER_ROTATION = 240;
    // radius = 2, multiplied by 2pi, divided by 12 to convert to feet.
    public static final float WHEEL_CIRCUMFERENCE_FEET = (float)((2*2*Math.PI)/12);
    public static final float TICKS_TO_FEET = WHEEL_CIRCUMFERENCE_FEET / TICKS_PER_ROTATION;
    
    Talon left = new Talon(RobotMap.DRIVE_LEFT);
    Talon right = new Talon(RobotMap.DRIVE_RIGHT);
    Gyro gyro = new Gyro(RobotMap.DRIVE_GYRO);
    Accelerometer accel = new Accelerometer(RobotMap.DRIVE_ACCEL);
    Encoder leftEncoder = new Encoder(RobotMap.DRIVE_LEFT_ENCODER_A,
                                        RobotMap.DRIVE_LEFT_ENCODER_B);
    Encoder rightEncoder = new Encoder(RobotMap.DRIVE_RIGHT_ENCODER_A,
                                        RobotMap.DRIVE_RIGHT_ENCODER_B);
    Solenoid lowSpeedSolenoid = new Solenoid(RobotMap.DRIVE_LOW_SPEED_SOLENOID);
    Solenoid highSpeedSolenoid = new Solenoid(RobotMap.DRIVE_HIGH_SPEED_SOLENOID);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CommandArcadeDrive());
    }
    
    public void setLeft(double power){
        this.left.set(power);
    }
    
    public void setRight(double power){
        this.right.set(-power);
    }
    
    public void setHighSpeed(){
        highSpeedSolenoid.set(true);
        lowSpeedSolenoid.set(false);
    }
    
    /**
     * Returns the speed of the robot in feet per second.
     * @return 
     */
    public float getSpeed(){
        float ticks = (float)(leftEncoder.getRate() + rightEncoder.getRate());
        ticks /= 2;
        return TICKS_TO_FEET * ticks;
    }
    
    public void setLowSpeed(){
        lowSpeedSolenoid.set(true);
        highSpeedSolenoid.set(false);
    }
    
    public float getGyroAngle(){
        return (float)gyro.getAngle();
    }
    
    public void resetGyro(){
        gyro.reset();
    }
    
    public boolean isHighSpeed(){
        return highSpeedSolenoid.get();
    }
}

