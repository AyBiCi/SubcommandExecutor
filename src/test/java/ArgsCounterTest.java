import com.github.aybici.ArgsCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArgsCounterTest {

    @Test
    public void testCreatingArgsCount(){
        assertArrayEquals( new int[]{0},ArgsCounter.countArgs("") );

        assertArrayEquals( new int[]{1},ArgsCounter.countArgs("<abc>") );
        assertArrayEquals( new int[]{2},ArgsCounter.countArgs("<abc> <cde>") );
        assertArrayEquals( new int[]{3},ArgsCounter.countArgs("<abc> <cde> <fgh>") );

        assertArrayEquals( new int[]{2,3},ArgsCounter.countArgs("<abc> <cde> [fgh]") );
        assertArrayEquals( new int[]{2,3,4},ArgsCounter.countArgs("<abc> <cde> [fgh] [abc]") );
        assertArrayEquals( new int[]{2,3,4,5},ArgsCounter.countArgs("<abc> <cde> [fgh] [mgh] [cde]") );
    }

}
