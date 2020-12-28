# SubcommandExecutor
Module for Bukkit plugins. It's a tool to create commands with subcommands easily.

# Features
<ul>
<li>Auto-generation of a help command</li>
<li>Check of a argument count</li>
</ul>

# Maven
Will be added soon...
# How to use it
```java
public void onEnable(){
   SubcommandExecutor executor = new SubcommandExecutor("test");
         
        //Default executor will be executed when there's no subcommand
        //with name that user used
        executor.setDefaultExecutor((commandSender, command, s, args) -> {
            commandSender.sendMessage("Hello world!");
            return false;
        });
        
        //standard subcommand
        executor.addCommandExecutor
                (new Subcommand(
                    "add",             //Subcommand name
                    "<name>",          //Argument list
                    "adds new test",   //Description used in help command
                    (commandSender, command, s, args) -> { //CommandExecutor class in lambda
                        commandSender.sendMessage("adding "+args[0]);
                        return true;
                    }
                ));
        
        executor.addCommandExecutor
                (new Subcommand(
                    "remove",
                    "<name>",
                    "removes test",
                    (commandSender, command, s, args) -> {
                            commandSender.sendMessage("removing "+args[0]);
                            return true;
                    }
               ));
        getCommand("test").setExecutor(executor); //Setting the executor for command "test"
}
```
