import org.junit.jupiter.api.Assertions;

class MinesweeperModelTest {
    private MinesweeperModel minesweeperModel;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        minesweeperModel = new MinesweeperModel();
    }

    @org.junit.jupiter.api.Test
    void getMineLocation() {
        Assertions.assertEquals(11, minesweeperModel.getMineLocation().length);
    }

    @org.junit.jupiter.api.Test
    void setMineLocation() {
        minesweeperModel.setMineLocation(new int[2][3]);
        Assertions.assertEquals(2, minesweeperModel.getMineLocation().length);
    }

    @org.junit.jupiter.api.Test
    void getEmptyElement() {
        Assertions.assertEquals(11, minesweeperModel.getEmptyElement().length);
    }

    @org.junit.jupiter.api.Test
    void setEmptyElement() {
        minesweeperModel.setEmptyElement(new int[2][2]);
        Assertions.assertEquals(11, minesweeperModel.getMineLocation().length);
    }

    @org.junit.jupiter.api.Test
    void constrOne() {
        minesweeperModel = new MinesweeperModel(new int[3][3]);
        Assertions.assertEquals(11, minesweeperModel.getMineLocation().length);
    }

    @org.junit.jupiter.api.Test
    void constrTwo() {
        minesweeperModel = new MinesweeperModel(3, 4);
        Assertions.assertEquals(11, minesweeperModel.getMineLocation().length);
    }

    @org.junit.jupiter.api.Test
    void reAssignMineList() {
        minesweeperModel.reAssignMineList(new int[3][3], 2, 2);
        Assertions.assertEquals(11, minesweeperModel.getMineLocation().length);
    }

    @org.junit.jupiter.api.Test
    void generateMineList() {
        minesweeperModel.generateMineList(new int[11][11]);
        Assertions.assertEquals(11, minesweeperModel.getMineLocation().length);
    }

    @org.junit.jupiter.api.Test
    void generateRoles() {
        minesweeperModel.generateRoles(3);
        Assertions.assertEquals(11, minesweeperModel.roles.length);
    }

    @org.junit.jupiter.api.Test
    void generateRoundMineCount() {
        minesweeperModel.generateRoundMineCount();
        Assertions.assertEquals(11, minesweeperModel.roles.length);
    }
}