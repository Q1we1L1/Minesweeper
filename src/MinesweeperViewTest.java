import org.junit.jupiter.api.BeforeEach;

class MinesweeperViewTest {
    private MinesweeperModel minesweeperModel;
    private MinesweeperController minesweeperController;
    private MinesweeperView minesweeperView;

    @BeforeEach
    void setUp() {
        minesweeperModel = new MinesweeperModel();
        minesweeperController = new MinesweeperController(minesweeperModel);
        minesweeperView = new MinesweeperView();
        minesweeperView.setMinesweeperModel(minesweeperModel);
        minesweeperView.setMinesweeperController(minesweeperController);
    }

}