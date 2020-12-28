import com.github.aybici.Subcommand;
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
                (new Subcommand(
                        "add",
                    "<name>",
                    "adds new test",
                    (commandSender, command, s, strings) -> {
                        commandSender.sendMessage("add "+strings[0]);
                        return true;
                    }
                ));

        executor.addCommandExecutor
                (new Subcommand(
                        "remove",
                    "<name>",
                    "removes test",
                    (commandSender, command, s, strings) -> {
                            commandSender.sendMessage("remove "+strings[0]);
                            return true;
                        }
               ));

        executor.addCommandExecutor
                (new Subcommand(
                        "marek",
                        "",
                        "marek",
                        null
                ));
        getCommand("test").setExecutor(executor);
    }

    protected PluginTestClass(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
    }
}
