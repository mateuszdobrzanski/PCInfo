/**
 *
 * This class is responsible for execute cmd commands
 * runCmdCommand() method open cmd window and execute the command without closing a cmd window
 * executeCmdCommand() method is executing a command and automatically close cmd promt
 *
 */

package com.demo;

public class cmdHandler {

    public cmdHandler() {
    }

    // Open only cmd window
    public static void runCmdCommand(){
        try
        {
            Runtime.getRuntime().exec(new String[] {"cmd", "/K", "start"});
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // open cmd window and execute of the command given
    public static void runCmdCommand(String command){
        try
        {
            Runtime.getRuntime().exec("cmd /c start cmd.exe /K " + command);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // execute the command (without interaction with cmd window - auto close)
    public static void executeCmdCommand(String command){
        try
        {
            Runtime.getRuntime().exec("cmd /c " + command);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
