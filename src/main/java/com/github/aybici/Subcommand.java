package com.github.aybici;

import org.bukkit.command.CommandExecutor;

public class Subcommand {
    private String name;
    private CommandExecutor executor;
    private String description;
    private String argsString;
    private int[] possibleArgsCount;

    public Subcommand(String name,String argsString, String description, int[] possibleArgsCount, CommandExecutor executor){
        this.name = name;
        this.argsString = argsString;
        this.description = description;
        this.possibleArgsCount = possibleArgsCount;
        this.executor = executor;
    }

    public String getName(){
        return name;
    }
    public CommandExecutor getExecutor(){
        return executor;
    }
    public String getDescription(){
        return description;
    }
    public int[] getPossibleArgsCount(){
        return possibleArgsCount;
    }
    public String getArgsString(){return argsString;}
}
