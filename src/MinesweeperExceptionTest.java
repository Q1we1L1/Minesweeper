import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinesweeperExceptionTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testException() {
        MinesweeperException minesweeperException = new MinesweeperException("xxx", "XXX");
        Assertions.assertEquals(minesweeperException.getMessage(),"xxx");
    }

}