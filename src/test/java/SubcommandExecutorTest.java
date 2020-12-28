import com.github.aybici.Subcommand;
import com.github.aybici.SubcommandExecutor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubcommandExecutorTest {

    SubcommandExecutor executor;

    @BeforeEach
    public void setup(){
        executor = new SubcommandExecutor("yourCommandName");
    }

    @Test
    public void realLifeTest(){
        final String[] testString = new String[1];

        executor.addCommandExecutor(
                new Subcommand(
                "create",
                "<name>",
                "description of a command",
                new int[]{1}, // Command gets one parameter, none parameters or more than 1 will be error
                (commandSender, command, s, strings) -> {
                    testString[0] = "create";
                    return false;
                }));

        executor.addCommandExecutor(
                new Subcommand(
                "delete",
                "<name>",
                "description of a command",
                new int[]{1},
                (commandSender, command, s, strings) -> {
                    testString[0] = "delete";
                    return false;
                }));

        executor.onCommand(null, null, null, new String[]{"create"});
        Assertions.assertEquals("create", testString[0]);

        executor.onCommand(null, null, null, new String[]{"delete"});
        Assertions.assertEquals("delete", testString[0]);
    }

    @Test
    public void firstCliSubcommandExecutorTest(){

        executor.addCommandExecutor(
                new Subcommand(
                "subcommand",
                "<argument>",
                "description of a command",
                new int[]{1},
                (commandSender, command, s, strings) -> false)); // Just not null

        String[] args = new String[]{"subcommand"};
        executor.onCommand(null,null,null, args);
    }

    @Test
    public void checkExecution(){
        final Boolean[] executed = {false};

        executor.addCommandExecutor(
                new Subcommand(
                "subcommand",
                "<argument>",
                "description of a command",
                new int[]{1},
                new CommandExecutor() {
                    @Override
                    public boolean onCommand(CommandSender commandSender,Command command, String s, String[] strings) {
                        executed[0] = true;
                        return false;
                    }
                }));

        executor.onCommand(null,null,null, new String[]{"subcommand"});

        Assertions.assertTrue(executed[0]);
    }

    @Test
    public void testThrowNoExecutor(){
        testThrowSubname("makrejsl");
        testThrowSubname("asdgs");
        testThrowSubname("makrsdfgsdfejsl");
        testThrowSubname("makrasdfsadfejsl");
    }

    private void testThrowSubname(String name){
        SubcommandExecutor.NoExecutorForCommand exception =
                Assertions.assertThrows(SubcommandExecutor.NoExecutorForCommand.class, () -> {
                    executor.onCommand(null, null, null, new String[]{name});
                });

        Assertions.assertEquals(name,exception.getSubcommandName());
        Assertions.assertEquals("No executor for subcommand \""+name+"\"!", exception.getMessage());
    }

    @Test
    public void testStandardCommand(){
        final String[] testString = new String[1];
        executor.setDefaultExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
                testString[0] = "lol";
                return false;
            }
        });
        executor.onCommand(null, null, null, new String[]{});
        Assertions.assertEquals("lol", testString[0]);
    }


}
