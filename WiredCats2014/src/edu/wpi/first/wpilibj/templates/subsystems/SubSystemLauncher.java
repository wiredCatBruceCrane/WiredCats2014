/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;

/**
 *
 * @author WiredCats
 */
public class SubSystemLauncher extends Subsystem {
    
    
    Victor[] winch = new Victor[2];
    Solenoid launcherExtend = new Solenoid(RobotMap.LAUNCHER_RELEASE_SOLENOID_EXTEND);
    Solenoid launcherRetract = new Solenoid(RobotMap.LAUNCHER_RELEASE_SOLENOID_RETRACT);
    DigitalInput limitSwitch = new DigitalInput(RobotMap.LAUNCHER_LIMIT_SWITCH);
    
    public SubSystemLauncher(){
        winch[0] = new Victor(RobotMap.LAUNCHER_WINCH_MOTOR_1);
        winch[1] = new Victor(RobotMap.LAUNCHER_WINCH_MOTOR_2);
    }

    protected void initDefaultCommand() {

    }
    
    public void cock(){
        set(1.0);
    }
    
    public boolean isCocking(){
        return winch[0].get() > 0.0;
    }
    
    public void stopCocking(){
        set(0.0);
    }
    
    public void set(double d){
        winch[0].set(d);
        winch[1].set(d);
    }
    
    public boolean hasHitLimit(){
        System.out.println("LimitSwitch value: " + limitSwitch.get());
        return limitSwitch.get();
    }
    
    public void launch(){
        if (!CommandBase.ldisubsystem.isExtended()){
            System.out.println("Warning! Tried to launch when the "
                    + "LDI was not extended! Launch aborted.");
            return;
        }
        launcherExtend.set(true);
        launcherRetract.set(false);
    }
    
    /**
     * Ay Ay Ay, how you doin' baybe?
     */
    public void engageWench(){
        launcherExtend.set(false);
        launcherRetract.set(true);
    }
}