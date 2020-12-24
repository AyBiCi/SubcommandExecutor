package com.github.aybici;

import org.bukkit.command.CommandExecutor;

public class Subcommand {
    public String name;
    public CommandExecutor executor;
    public String description;
    public int[] possibleArgsCount;

    public Subcommand(String name, CommandExecutor executor, String description, int[] possibleArgsCount){
        this.name = name;
        this.executor = executor;
        this.description = description;
        this.possibleArgsCount = possibleArgsCount;
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
}
