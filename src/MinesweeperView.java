import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MinesweeperView extends Application {
    public static final int SIZE = 11;
    private Clock timer;
    private MinesweeperModel minesweeperModel;
    private MinesweeperController minesweeperController;
    private MSImage[][] clickView;
    private MSImage[][] emptyView;
    private MSImage[][] flagView;
    private int startCheck = 0;
    private int x;
    private int y;
    private int correctFlag = 0;
    int clickCount = 0;
    int flagCount = 9;
    private Stage newStage;


    public MinesweeperView() {

        clickView = new MSImage[SIZE][SIZE];
        emptyView = new MSImage[SIZE][SIZE];
        flagView = new MSImage[SIZE][SIZE];
        timer = new Clock();
    }

    public MinesweeperModel getMinesweeperModel() {
        return minesweeperModel;
    }

    public void setMinesweeperModel(MinesweeperModel minesweeperModel) {
        this.minesweeperModel = minesweeperModel;
    }

    public MinesweeperController getMinesweeperController() {
        return minesweeperController;
    }

    public void setMinesweeperController(MinesweeperController minesweeperController) {
        this.minesweeperController = minesweeperController;
    }

    public static void main(String[] args) {
        launch(args);
    }


    void imageInit(MSImage[][] clickView, int[][] mineLocation) {

        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                if (mineLocation[i][j] == -2) {
                    clickView[i][j] = new MSImage("/image/9.png");
                } else if (mineLocation[i][j] == -1) {
                    clickView[i][j] = new MSImage("/image/12.png");
                } else if (mineLocation[i][j] == 0) {
                    clickView[i][j] = new MSImage("/image/0.png");
                } else if (mineLocation[i][j] == 1) {
                    clickView[i][j] = new MSImage("/image/1.png");
                } else if (mineLocation[i][j] == 2) {
                    clickView[i][j] = new MSImage("/image/2.png");
                } else if (mineLocation[i][j] == 3) {
                    clickView[i][j] = new MSImage("/image/3.png");
                } else if (mineLocation[i][j] == 4) {
                    clickView[i][j] = new MSImage("/image/4.png");
                } else if (mineLocation[i][j] == 5) {
                    clickView[i][j] = new MSImage("/image/5.png");
                } else if (mineLocation[i][j] == 6) {
                    clickView[i][j] = new MSImage("/image/6.png");
                } else if (mineLocation[i][j] == 7) {
                    clickView[i][j] = new MSImage("/image/7.png");
                } else if (mineLocation[i][j] == 8) {
                    clickView[i][j] = new MSImage("/image/8.png");
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
                    clickView[i][j] = new MSImage("/image/0.png");
                }
            }
        }

    }

    void emptyView(MSImage[][] emptyList) {

        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                emptyList[i][j] = new MSImage("/image/10.png");
            }
        }
    }

    void flagView(MSImage[][] flagView) {
        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                flagView[i][j] = new MSImage("/image/11.png");
            }
        }
    }

    void initEmptyList() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                minesweeperModel.getEmptyElement()[i][j] = 0;
            }
    }

    void gameState() {
        correctFlag = 0;
        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                if (minesweeperModel.getEmptyElement()[i][j] == 1)
                    correctFlag++;
            }
        }
        if (correctFlag == 72) {
            initEmptyList();
            startCheck = 0;
        }
    }

    void openALlMineLocations() {

        for (int i = 1; i < SIZE - 1; i++) {
            for (int j = 1; j < SIZE - 1; j++) {
                if (minesweeperModel.getMineLocation()[i][j] == -2) {
                    minesweeperModel.getDisplayElements()[i][j].setImage(clickView[i][j]);
                    minesweeperModel.getDisplayElements()[i][j].url = clickView[i][j].url;

                }
                if (minesweeperModel.getMineLocation()[i][j] == -1) {
                    minesweeperModel.getDisplayElements()[i][j].setImage(new MSImage("/image/12.png"));
                    minesweeperModel.getDisplayElements()[i][j].url = "/image/12.png";
                }
            }
        }

    }

    void noMineLocationUpdate(MinesweeperImage[][] imageView, MSImage[][] images, int[][] mines, int x, int y) {

        if (x >= 1 && x <= SIZE - 2 && y >= 1 && y <= SIZE - 2) {

            imageView[x][y].setImage(images[x][y]);
            imageView[x][y].url = images[x][y].url;
            minesweeperModel.getEmptyElement()[x][y] = 1;

            if (x + 1 <= SIZE - 2) {
                imageView[x + 1][y].setImage(images[x + 1][y]);
                imageView[x + 1][y].url = images[x + 1][y].url;
                if (mines[x + 1][y] != 0) {
                    minesweeperModel.getEmptyElement()[x + 1][y] = 1;
                }
            }
            if (x - 1 >= 1) {
                imageView[x - 1][y].setImage(images[x - 1][y]);
                imageView[x - 1][y].url = images[x - 1][y].url;
                if (mines[x - 1][y] != 0) {
                    minesweeperModel.getEmptyElement()[x - 1][y] = 1;
                }
            }
            if (y + 1 <= SIZE - 2) {
                imageView[x][y + 1].setImage(images[x][y + 1]);
                imageView[x][y + 1].url = images[x][y + 1].url;
                if (mines[x][y + 1] != 0) {
                    minesweeperModel.getEmptyElement()[x][y + 1] = 1;
                }
            }
            if (y - 1 >= 1) {
                imageView[x][y - 1].setImage(images[x][y - 1]);
                imageView[x][y - 1].url = images[x][y - 1].url;
                if (mines[x][y - 1] != 0) {
                    minesweeperModel.getEmptyElement()[x][y - 1] = 1;
                }
            }
            if (x + 1 <= SIZE - 2 && y + 1 <= SIZE - 2) {
                imageView[x + 1][y + 1].setImage(images[x + 1][y + 1]);
                imageView[x + 1][y + 1].url = images[x + 1][y + 1].url;
                if (mines[x + 1][y + 1] != 0) {
                    minesweeperModel.getEmptyElement()[x + 1][y + 1] = 1;
                }
            }
            if (x - 1 >= 1 && y - 1 >= 1) {
                imageView[x - 1][y - 1].setImage(images[x - 1][y - 1]);
                imageView[x - 1][y - 1].url = images[x - 1][y - 1].url;
                if (mines[x - 1][y - 1] != 0) {
                    minesweeperModel.getEmptyElement()[x - 1][y - 1] = 1;
                }
            }
            if (x + 1 <= SIZE - 2 && y - 1 >= 1) {
                imageView[x + 1][y - 1].setImage(images[x + 1][y - 1]);
                imageView[x + 1][y - 1].url = images[x + 1][y - 1].url;
                if (mines[x + 1][y - 1] != 0) {
                    minesweeperModel.getEmptyElement()[x + 1][y - 1] = 1;
                }
            }
            if (x - 1 >= 1 && y + 1 <= SIZE - 2) {
                imageView[x - 1][y + 1].setImage(images[x - 1][y + 1]);
                imageView[x - 1][y + 1].url = images[x - 1][y + 1].url;
                if (mines[x - 1][y + 1] != 0) {
                    minesweeperModel.getEmptyElement()[x - 1][y + 1] = 1;
                }
            }

            if (mines[x + 1][y] == 0 && x + 1 <= SIZE - 2 && minesweeperModel.getEmptyElement()[x + 1][y] != 1) {
                noMineLocationUpdate(imageView, images, mines, x + 1, y);
            }
            if (mines[x - 1][y] == 0 && x - 1 >= 1 && minesweeperModel.getEmptyElement()[x - 1][y] != 1) {
                noMineLocationUpdate(imageView, images, mines, x - 1, y);
            }
            if (mines[x][y + 1] == 0 && y + 1 <= SIZE - 2 && minesweeperModel.getEmptyElement()[x][y + 1] != 1) {
                noMineLocationUpdate(imageView, images, mines, x, y + 1);
            }
            if (mines[x][y - 1] == 0 && y - 1 >= 1 && minesweeperModel.getEmptyElement()[x][y - 1] != 1) {
                noMineLocationUpdate(imageView, images, mines, x, y - 1);
            }
            if (mines[x + 1][y + 1] == 0 && x + 1 <= SIZE - 2 && y + 1 <= SIZE - 2 && minesweeperModel.getEmptyElement()[x + 1][y + 1] != 1) {
                noMineLocationUpdate(imageView, images, mines, x + 1, y + 1);
            }
            if (mines[x - 1][y - 1] == 0 && x - 1 >= 1 && y - 1 >= 1 && minesweeperModel.getEmptyElement()[x - 1][y - 1] != 1) {
                noMineLocationUpdate(imageView, images, mines, x - 1, y - 1);
            }
            if (mines[x + 1][y - 1] == 0 && x + 1 <= SIZE - 2 && y - 1 >= 1 && minesweeperModel.getEmptyElement()[x + 1][y - 1] != 1) {
                noMineLocationUpdate(imageView, images, mines, x + 1, y - 1);
            }
            if (mines[x - 1][y + 1] == 0 && x - 1 >= 1 && y + 1 <= SIZE - 2 && minesweeperModel.getEmptyElement()[x - 1][y + 1] != 1) {
                noMineLocationUpdate(imageView, images, mines, x - 1, y + 1);
            }
        }
    }

    void unZeroAction() {

        minesweeperModel.getDisplayElements()[x][y].setImage(clickView[x][y]);
        minesweeperModel.getDisplayElements()[x][y].url = clickView[x][y].url;
        minesweeperModel.getEmptyElement()[x][y] = 1;

    }

    @Override
    public void start(Stage primaryStage) {
        minesweeperModel = new MinesweeperModel();
        minesweeperController = new MinesweeperController(minesweeperModel);
        GridPane gridPane = new GridPane();
        Pane pane = new Pane();
        timer.setLayoutX(500);
        timer.setLayoutY(40);
        timer.setMaxHeight(120 + 20);
        timer.setMaxWidth(250);
        Label label = new Label("Mine: " + flagCount);
        label.setLayoutX(500);
        label.setLayoutY(100);
        label.setFont(javafx.scene.text.Font.font(20));
        Button beginButton = new Button();
        beginButton.setText("Start Game");
        beginButton.setLayoutX(500);
        beginButton.setLayoutY(180);
        beginButton.setMinHeight(20);
        beginButton.setMinWidth(120);

        Button resButton = new Button();
        resButton.setText("Reset Game");
        resButton.setLayoutX(500);
        resButton.setLayoutY(230);
        resButton.setMinWidth(120);
        resButton.setMinHeight(20);

        Button exitGame = new Button();
        exitGame.setText("Exit Game");
        exitGame.setLayoutX(500);
        exitGame.setLayoutY(280);
        exitGame.setMinWidth(120);
        exitGame.setMinHeight(20);

        Label score = new Label();
        score.setText(minesweeperModel.getScoresString());
        score.setLayoutX(500);
        score.setLayoutY(330);
        score.setMinWidth(120);
        score.setMinHeight(20);
        score.setFont(javafx.scene.text.Font.font(15));
        emptyView(emptyView);
        flagView(flagView);

        minesweeperModel.generateMineList(minesweeperModel.getMineLocation());
        imageInit(clickView, minesweeperModel.getMineLocation());
        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(MinesweeperModel.FILE_NAME));
            MinesweeperModel minesweeperModel1 = (MinesweeperModel) objectInputStream.readObject();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
                        minesweeperModel.getDisplayElements()[i][j] = new MinesweeperImage(emptyView[i][j]);
                    } else {
                        minesweeperModel.getDisplayElements()[i][j] = new MinesweeperImage(minesweeperModel1.getBoardStatus()[i][j]);
                        minesweeperModel.getDisplayElements()[i][j].setFitHeight(50);
                        minesweeperModel.getDisplayElements()[i][j].setFitWidth(50);
                        gridPane.add(minesweeperModel.getDisplayElements()[i][j], i, j);
                    }
                }
            }
            startCheck = 1;
            objectInputStream.close();
        } catch (Exception e) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (i == 0 || i == SIZE - 1 || j == 0 || j == SIZE - 1) {
                        minesweeperModel.getDisplayElements()[i][j] = new MinesweeperImage(emptyView[i][j]);
                    } else {
                        minesweeperModel.getDisplayElements()[i][j] = new MinesweeperImage(emptyView[i][j]);
                        minesweeperModel.getDisplayElements()[i][j].setFitHeight(50);
                        minesweeperModel.getDisplayElements()[i][j].setFitWidth(50);
                        gridPane.add(minesweeperModel.getDisplayElements()[i][j], i, j);
                    }
                }
            }

        }


        pane.getChildren().add(gridPane);
        pane.getChildren().addAll(beginButton, resButton, timer, exitGame, label, score);

        gridPane.layoutXProperty();
        gridPane.layoutYProperty();

        Scene scene = new Scene(pane, 690, 490);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MinesweeperView");
        primaryStage.setMinWidth(690);
        primaryStage.setMinHeight(490);
        primaryStage.setMaxWidth(690);
        primaryStage.setMaxHeight(490);
        primaryStage.show();

        gridPane.setOnMouseClicked(event -> {
            if (startCheck == 0) {
                return;
            }
            x = (int) (Math.floor(event.getX() / 50) + 1);
            y = (int) (Math.floor(event.getY() / 50) + 1);

            if (event.getButton() == MouseButton.PRIMARY) {
                if (clickCount == 0) {
                    clickCount = 1;
                }
                if (minesweeperModel.getDisplayElements()[x][y].getImage() == flagView[x][y]) {
                    return;
                }

                if (startCheck == 1 && minesweeperModel.getMineLocation()[x][y] != -2) {
//                    showNewStage(primaryStage);
                    if (minesweeperModel.getMineLocation()[x][y] != 0) {
                        unZeroAction();
                    } else {
                        noMineLocationUpdate(minesweeperModel.getDisplayElements(), clickView, minesweeperModel.getMineLocation(), x, y);
                    }
                } else if (startCheck == 1 && minesweeperModel.getMineLocation()[x][y] == -2) {
                    if (clickCount == 0) {
                        minesweeperModel.reAssignMineList(minesweeperModel.getMineLocation(), x, y);
                        imageInit(clickView, minesweeperModel.getMineLocation());
                        clickCount = 1;
                        return;
                    }

                    alertLose(timer);
                    minesweeperModel.getMineLocation()[x][y] = -1;
                    clickCount = 1;
                    openALlMineLocations();
                    gameState();
                    startCheck = 0;
                }
                gameState();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                if (clickCount == 0) {
                    return;
                }
                if (startCheck == 1) {
                    if (minesweeperModel.getDisplayElements()[x][y].getImage() == emptyView[x][y]) {
                        if (flagCount == 0) {
                            return;
                        }
                        minesweeperModel.getDisplayElements()[x][y].setImage(flagView[x][y]);
                        minesweeperModel.getDisplayElements()[x][y].url = flagView[x][y].url;
                        flagCount -= 1;
                        label.setText("Mine: " + flagCount);
                    } else if (minesweeperModel.getDisplayElements()[x][y].getImage() == flagView[x][y]) {
                        minesweeperModel.getDisplayElements()[x][y].setImage(emptyView[x][y]);
                        minesweeperModel.getDisplayElements()[x][y].url = emptyView[x][y].url;
                        flagCount += 1;
                        label.setText("Mine: " + flagCount);
                    }
                }
                int checkFlag = 0;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (minesweeperModel.getMineLocation()[x][y] == -2 && minesweeperModel.getDisplayElements()[x][y].getImage() == flagView[x][y]) {
                            checkFlag += 1;
                        }
                    }
                }
                if (checkFlag == 9) {
                    alertWin();
                    timer.timeline.stop();
                    minesweeperModel.getScores().add(timer.timeSeconds);
                    score.setText(minesweeperModel.getScoresString());
                }
                showNewStage(primaryStage);
            }

        });

        beginButton.setOnAction(event -> {

            timer.setTimeSeconds(60);
            timer.start();
            beginButton.setDisable(true);
            initEmptyList();
            startCheck = 1;

        });

        resButton.setOnAction(event -> {
            beginButton.setDisable(false);
            timer.getTimeline().stop();
            timer.setTimeSeconds(60);
            timer.getLabel().setText("");
            flagCount = 9;
            label.setText("Mine: " + flagCount);
            minesweeperModel.generateMineList(minesweeperModel.getMineLocation());
            imageInit(clickView, minesweeperModel.getMineLocation());
            for (int i = 1; i < SIZE - 1; i++) {
                for (int j = 1; j < SIZE - 1; j++) {
                    minesweeperModel.getDisplayElements()[i][j].setImage(emptyView[i][j]);
                    minesweeperModel.getDisplayElements()[i][j].url = emptyView[i][j].url;
                }
            }
            startCheck = 0;
            initEmptyList();
        });

        exitGame.setOnAction(event -> primaryStage.close());
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {

                    minesweeperModel.setBoardStatus(transferDisplayToModel());
                    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(MinesweeperModel.FILE_NAME));
                    outputStream.writeObject(minesweeperModel);
                    outputStream.close();
                    System.exit(-1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void showNewStage(Stage primaryStage) {
        newStage = new Stage();
        Pane pane = new Pane();
        Scene sceneNew = new Scene(pane, 690, 490);
        Label label = new Label("Thinking, Thinking, Thinking...\nThen close this large wall...");
        label.setFont(javafx.scene.text.Font.font(40));
        label.setLayoutY(150);
        label.setLayoutX(50);
        pane.getChildren().add(label);
        newStage.setAlwaysOnTop(true);
        newStage.setTitle("MinesweeperView");
        newStage.setScene(sceneNew);

        Platform.setImplicitExit(false);
        newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.show();
                newStage.close();
            }
        });
        primaryStage.hide();
        newStage.showAndWait();
    }

    public String[][] transferDisplayToModel() {
        String[][] imagePath = new String[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (minesweeperModel.getDisplayElements()[i][j].getImage() != null) {
                    imagePath[i][j] = minesweeperModel.getDisplayElements()[i][j].url;
//                    System.out.println(imagePath[i][j]);
                }

            }
        }
        return imagePath;
    }

    private static void alertLose(Clock timer) {
        timer.timeline.stop();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("You Lose!");

        alert.setContentText("You Lose!");
        alert.showAndWait();
    }

    private static void alertWin() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Info");
        alert.setHeaderText("You Win!");
        alert.setContentText("You Win!");
        alert.showAndWait();
    }

    static class Clock extends Pane {

        private Timeline timeline;
        private int timeSeconds = 60;

        Label label = new Label("");

        public Clock() {
            label.setFont(javafx.scene.text.Font.font(20));
            getChildren().add(label);

        }

        public void start() {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeDisplay()));
            timeline.setCycleCount(timeSeconds);
            timeline.play();
            timeline.setOnFinished(event -> {
                label.setText("Time Over!");
                alertLose(this);
            });

        }

        public void timeDisplay() {
            String text = "time: " + timeSeconds + " s";
            timeSeconds--;
            label.setText(text);
        }

        public Timeline getTimeline() {
            return timeline;
        }


        public Label getLabel() {
            return label;
        }

        public void setTimeSeconds(int timeSeconds) {
            this.timeSeconds = timeSeconds;
        }
    }
}
