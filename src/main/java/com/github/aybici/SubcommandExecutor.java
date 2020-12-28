package com.github.aybici;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;

public class SubcommandExecutor implements CommandExecutor{

    private final HashMap<String,Subcommand> executors = new HashMap<>();
    private String commandName;
    private CommandExecutor defaultExecutor;

    public SubcommandExecutor(String commandName) {
        this.commandName = commandName;
        addHelpCommand();
    }

    public void setDefaultExecutor(CommandExecutor newDefaultExecutor) {
        defaultExecutor = newDefaultExecutor;
    }

    public void addCommandExecutor(Subcommand subcommand) {
        subcommand.setParentCommandName(commandName);
        executors.put(subcommand.getName(), subcommand);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        String subcommandName = "";
        if(args.length != 0){
            subcommandName = args[0];
        }

        String[] newArgs = (String[]) ArrayUtils.subarray(args,1,args.length);

        if(hasExecutor(subcommandName)){
            Subcommand executor = getExecutor(subcommandName);
            executor.getExecutor().onCommand(commandSender, command, s, newArgs);
            return true;
        }
        else if(hasDefaultExecutor()){
            defaultExecutor.onCommand(commandSender, command, s, newArgs);
            return true;
        }

        throw new NoExecutorForCommand(subcommandName);
    }

    public static class NoExecutorForCommand extends RuntimeException{

        private final String subcommandName;

        public NoExecutorForCommand(String subcommandName){
            this.subcommandName = subcommandName;
        }

        public String getSubcommandName() {
            return subcommandName;
        }

        @Override
        public String getMessage(){
            return "No executor for subcommand \""+subcommandName+"\"!";
        }
    }

    private boolean hasExecutor(String subcommand) {
        return executors.containsKey(subcommand);
    }

    private boolean hasDefaultExecutor() {
        return defaultExecutor != null;
    }

    private Subcommand getExecutor(String subcommand){
        return executors.get(subcommand);
    }

    private void addHelpCommand(){
        this.addCommandExecutor(new Subcommand("help",
                "",
                "shows all commands in plugin",
                new int[]{0},
                (commandSender, command, s, strings) -> {
                    commandSender.sendMessage("Command list for "+commandName+":");

                    HashMap<String,Subcommand> subcommands = new HashMap<>(executors);
                    subcommands.remove("help");

                    for(Subcommand subcommand : subcommands.values()){
                        commandSender.sendMessage(subcommand.createHelpString());
                    }
                    return true;
                }));
    }
}
