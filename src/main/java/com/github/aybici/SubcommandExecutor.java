package com.github.aybici;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class SubcommandExecutor implements CommandExecutor{

    private final HashMap<String,Subcommand> executors = new HashMap<>();
    private String commandName;
    private CommandExecutor defaultExecutor;
    private CommandExecutor helpCommandExecutor = (commandSender, command, s, strings) -> {
        commandSender.sendMessage("Lista subkomend dla komendy "+commandName);
        return true;
    };

    public SubcommandExecutor(String commandName) {
        this.commandName = commandName;
    }


    public void addCommandExecutor(String name, String description, int[] possibleArgsCount,CommandExecutor executor) {
        Subcommand subcommand = new Subcommand(name, executor, description, possibleArgsCount);
        executors.put(name, subcommand);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        String subcommandName = "";
        if(args.length != 0){
            subcommandName = args[0];
        }

        if(subcommandName.equals("help")){
            helpCommandExecutor.onCommand(commandSender, command, s, null);
            return true;
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

    private boolean hasDefaultExecutor() {
        return defaultExecutor != null;
    }

    private Subcommand getExecutor(String subcommand){
        return executors.get(subcommand);
    }

    public boolean hasExecutor(String subcommand) {
        return executors.containsKey(subcommand);
    }

    public void setDefaultExecutor(CommandExecutor newDefaultExecutor) {
        defaultExecutor = newDefaultExecutor;
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
}
