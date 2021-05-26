import java.io.Serializable;
import java.util.*;

public class MinesweeperModel implements Serializable {
    static final long serialVersionUID = 1L;
    public static final String FILE_NAME = "ms.dat";
    int[][] mineLocation;
    int[][] emptyElement;
    int[][] roles;
    private boolean[][] isDisplay;
    private Random random = new Random();
    private List<Integer> scores = new ArrayList<>();
    private String[][] boardStatus;
    private MinesweeperImage[][] displayElements;

    public MinesweeperImage[][] getDisplayElements() {
        return displayElements;
    }

    public void setDisplayElements(MinesweeperImage[][] displayElements) {
        this.displayElements = displayElements;
    }

    public MinesweeperModel() {
        displayElements = new MinesweeperImage[MinesweeperView.SIZE][MinesweeperView.SIZE];
        mineLocation = new int[11][11];
        emptyElement = new int[11][11];
        roles = new int[11][11];
        scores.add(10);
        scores.add(11);
        scores.add(12);
        scores.add(13);
    }

    public List<Integer> getScores() {
        return scores;
    }

    public String[][] getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(String[][] boardStatus) {
        this.boardStatus = boardStatus;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    public int[][] getMineLocation() {
        return mineLocation;
    }

    public void setMineLocation(int[][] mineLocation) {
        this.mineLocation = mineLocation;
    }

    public int[][] getEmptyElement() {
        return emptyElement;
    }

    public void setEmptyElement(int[][] emptyElement) {
        this.emptyElement = emptyElement;
    }

    public void reAssignMineList(int[][] mineList, int i, int j) {
        if (mineList[i][j] == -2) {
            mineList[i][j] = 0;
            mineList[i / 2][j] = -2;


        }
    }

    void generateMineList(int[][] mineList) {
        for (int i = 0; i < 11; i++)
            for (int j = 0; j < 11; j++) {
                mineList[i][j] = 0;
            }
        Random r = new Random();

        for (int i = 1; i <= 9; i++) {
            int first = r.nextInt(9) + 1;
            mineList[i][first] = -2;
        }

        for (int i = 1; i < 10; i++)
            for (int j = 1; j < 10; j++)
                if (mineList[i][j] != -2) {
                    if (mineList[i + 1][j] == -2) {
                        mineList[i][j]++;
                    }
                    if (mineList[i - 1][j] == -2)
                        mineList[i][j]++;
                    if (mineList[i][j + 1] == -2)
                        mineList[i][j]++;
                    if (mineList[i][j - 1] == -2)
                        mineList[i][j]++;
                    if (mineList[i + 1][j + 1] == -2)
                        mineList[i][j]++;
                    if (mineList[i - 1][j - 1] == -2)
                        mineList[i][j]++;
                    if (mineList[i + 1][j - 1] == -2)
                        mineList[i][j]++;
                    if (mineList[i - 1][j + 1] == -2)
                        mineList[i][j]++;
                }
    }

    public MinesweeperModel(int[][] roles) {
        this.roles = roles;
        mineLocation = new int[11][11];
        isDisplay = new boolean[roles.length][roles[0].length];
    }

    public MinesweeperModel(int width, int height) {
        this.roles = new int[width][height];
        mineLocation = new int[11][11];
        this.isDisplay = new boolean[width][height];
    }

    public void generateRoles(int mineCount) {
        Set<String> locations = new TreeSet<>();
        while (locations.size() <= mineCount) {
            int row = random.nextInt(roles.length);
            int col = random.nextInt(roles[0].length);
            locations.add(row + "_" + col);
        }
        for (String location : locations) {
            String[] strings = location.split("_");
            roles[Integer.parseInt(strings[0])][Integer.parseInt(strings[1])] = -1;
        }
    }

    public void generateRoundMineCount() {
        for (int i = 0; i < roles.length; i++) {
            for (int j = 0; j < roles[0].length; j++) {
                int count = 0;
                for (int l = i - 1; l < i + 2; l++) {
                    for (int p = j - 1; p < j + 2; p++) {
                        if (l > -1 && p > -1 && l < roles.length && p < roles[0].length && roles[l][p] == -1) {
                            count += 1;
                        }
                    }
                }
                if (roles[i][j] != -1) {
                    roles[i][j] = count;
                }
            }
        }
    }

    public String getScoresString() {
        String result = "ScoreRank\n";
        Collections.sort(scores);
        for (int i = 0; i < scores.size(); i++) {
            if (i > 2) {
                break;
            }
            result += ((i + 1) + ": " + scores.get(i) + " s\n");
        }
        return result;

    }
}
