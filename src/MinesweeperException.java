public class MinesweeperException extends Exception {
    private String msg;

    public MinesweeperException(String message, String msg) {
        super(message);
        this.msg = msg;
    }
}
