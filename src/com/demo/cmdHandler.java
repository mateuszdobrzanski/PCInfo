package com.demo;

public class cmdHandler {

    public cmdHandler() {
    }

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

}
