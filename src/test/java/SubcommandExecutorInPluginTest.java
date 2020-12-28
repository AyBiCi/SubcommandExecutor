import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubcommandExecutorInPluginTest {

    ServerMock server;
    PluginTestClass plugin;
    PlayerMock player;

    @BeforeEach
    public void setup(){
        server = MockBukkit.mock();
        plugin = (PluginTestClass) MockBukkit.load(PluginTestClass.class);
        player = server.addPlayer();
    }

    @AfterEach
    public void stopMock(){
        MockBukkit.unmock();
    }

    @Test
    public void testCommandExecution(){
        player.performCommand("test add abcd");
        assertEquals("add abcd", player.nextMessage());

        player.performCommand("test remove abcd");
        assertEquals("remove abcd", player.nextMessage());
    }

    @Test
    public void testDoubleSpaceHelpProblemOnNoArgsSubcommand(){
        player.performCommand("test help");

        assertEquals("Command list for test:",player.nextMessage());
        assertEquals("test add <name> - adds new test",player.nextMessage());
        assertEquals("test help - shows all commands in plugin",player.nextMessage());
        assertEquals("test marek - marek",player.nextMessage());
        assertEquals("test remove <name> - removes test",player.nextMessage());
    }

    @Test
    public void testUsageMessage(){
        player.performCommand("test add");
        assertEquals("Bad usage! Usage: test add <name>",player.nextMessage());
        player.performCommand("test remove");
        assertEquals("Bad usage! Usage: test remove <name>",player.nextMessage());
    }

    @Test
    public void testStandardExecutor(){
        player.performCommand("test abc");
        assertEquals("Command list - test help",player.nextMessage());
    }
}
