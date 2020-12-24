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

    @BeforeEach
    public void setup(){
        server = MockBukkit.mock();
        plugin = (PluginTestClass) MockBukkit.load(PluginTestClass.class);
    }

    @AfterEach
    public void stopMock(){
        MockBukkit.unmock();
    }


    @Test
    public void testHelpCommand(){
        PlayerMock player = server.addPlayer();

        player.performCommand("test help");

        assertEquals("Command list for test:",player.nextMessage());
        assertEquals("test add <name> - adds new test",player.nextMessage());
        assertEquals("test remove <name> - removes test",player.nextMessage());
    }

    @Test
    public void testCommandExecution(){
        PlayerMock player = server.addPlayer();

        player.performCommand("test add abcd");
        assertEquals("add abcd", player.nextMessage());

        player.performCommand("test remove abcd");
        assertEquals("remove abcd", player.nextMessage());
    }

}
