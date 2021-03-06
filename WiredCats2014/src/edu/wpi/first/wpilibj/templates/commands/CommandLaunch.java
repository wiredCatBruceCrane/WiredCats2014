/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author WiredCats
 */
public class CommandLaunch extends CommandBase {

    private static final double POST_LAUNCH_DELAY = 1.5;
    
    Timer t;
    
    public CommandLaunch(){
        requires(launchersubsystem);
        requires(ldisubsystem);
        setTimeout(POST_LAUNCH_DELAY);
        t = new Timer();
        setInterruptible(false);
    }
    
    protected void initialize() {
      ldisubsystem.extend_hood();
      ldisubsystem.extend_arm();
      ldisubsystem.motors_intake();
      t.start();
    }

    protected void execute() { 
        if (t.get() > 0.5){
            ldisubsystem.retract_hood();
        }
        if (t.get() > 0.85){
            ldisubsystem.extend_hood();
        }
        if (t.get() > 1.00){
            launchersubsystem.launch();
            t.stop();
            t.reset();
        }
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        ldisubsystem.setIntakeMotors(0);
    }

    protected void interrupted() {
        ldisubsystem.setIntakeMotors(0);

    }
    
}
