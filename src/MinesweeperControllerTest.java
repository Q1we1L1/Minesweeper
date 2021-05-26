import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MinesweeperControllerTest {
    private MinesweeperController minesweeperController;
    private MinesweeperModel minesweeperModel;

    @BeforeEach
    void setUp() {
        minesweeperModel = new MinesweeperModel();
        minesweeperController = new MinesweeperController(minesweeperModel);
    }

    @Test
    void checkWin() {
        Assertions.assertFalse(minesweeperController.checkWin());
    }

    @Test
    void update() {
        Assertions.assertFalse(minesweeperController.update());
    }

    @Test
    void clickCheck() {
        Assertions.assertFalse(minesweeperController.clickCheck());
    }

    @Test
    void getMinesweeperModel() {
        Assertions.assertEquals(minesweeperController.getMinesweeperModel().mineLocation.length, 11);
    }

    @Test
    void setMinesweeperModel() {
        minesweeperController.setMinesweeperModel(new MinesweeperModel());
    }
}