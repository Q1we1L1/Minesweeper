public class MinesweeperController {
    private MinesweeperModel minesweeperModel;

    public MinesweeperController(MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    public boolean checkWin() {
        return false;
    }

    public boolean update() {
        return false;
    }

    public boolean clickCheck() {
        return false;

    }

    public MinesweeperModel getMinesweeperModel() {
        return minesweeperModel;
    }

    public void setMinesweeperModel(MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }
}
