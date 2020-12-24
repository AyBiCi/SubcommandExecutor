import com.github.aybici.SubcommandExecutor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class PluginTestClass extends JavaPlugin {

    @Override
    public void onEnable() {
        SubcommandExecutor executor = new SubcommandExecutor("test");

        executor.addCommandExecutor
                (
                        "add",
                    "<name>",
                    "adds new test",
                    new int[]{1},
                    (commandSender, command, s, strings) -> {
                        commandSender.sendMessage("add "+strings[0]);
                        return true;
                    }
                );

        executor.addCommandExecutor
                (
                        "remove",
                    "<name>",
                    "removes test",
                    new int[]{1},
                    (commandSender, command, s, strings) -> {
                            commandSender.sendMessage("remove "+strings[0]);
                            return true;
                        }
               );

        getCommand("test").setExecutor(executor);
    }

    //Constuctors for MockBukkit
    public PluginTestClass()
    {
        super();
    }
    protected PluginTestClass(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
    }
}
