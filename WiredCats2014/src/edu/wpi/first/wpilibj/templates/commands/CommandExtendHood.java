/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author WiredCats
 */
public class CommandExtendHood extends CommandBase{

    public CommandExtendHood(){
        requires(ldisubsystem);
    }
    
    protected void initialize() {
       ldisubsystem.extend_hood();
       ldisubsystem.motors_intake();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {

    }
    
}
