import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.shape.Line;

public class App extends Application {

    private CueBall cueBall;
    private Ball CueBall;
    private DoubleProperty mouseX = new SimpleDoubleProperty();
    private DoubleProperty mouseY = new SimpleDoubleProperty();

    private Line currentLine = null;
    private boolean dragActive = false;
    Group root = new Group();
    boolean checkMovement = false;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Pool Game");

        ConfigReader cf = new ConfigReader();
        cf.parse("src/main/resources/config.json");

        Table table = cf.table;
        double borderW = 0.056 * table.getWidth();
        double borderH = 0.105 * table.getHeight();

        double w = table.getWidth() + 2 * borderW;
        double h = table.getHeight() + 2 * borderH;


        table.setX(borderW);
        table.setY(borderH);


        ImageView tableI = new ImageView();
//        tableI = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/table.png"))));
        tableI.setX(0);
        tableI.setY(0);
        tableI.setFitWidth(table.getWidth() + (2 * borderW));
        tableI.setFitHeight(table.getHeight() + (2 * borderH));

        root.getChildren().addAll(tableI, table);


        ArrayList balls = cf.balls;

        for (int i = 0; i < balls.size(); i++) {
            Ball newBall = (Ball) balls.get(i);
            if (newBall.getColor() == Color.WHITE) {
                CueBall = newBall;
            }
            root.getChildren().add(newBall);
        }

        cueBall = new CueBall(CueBall);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, w, h));


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {

                    double dx;
                    double dy;

                    Ballpit ch = new Ballpit();

                    Pocket p = new Pocket(table);


                    @Override
                    public void handle(ActionEvent t) {

                        for (Object ball : balls) {
                            Ball b = (Ball) ball;
                            dx = b.getVelX();
                            dy = b.getVelY();

                            if (dx > 0) {
                                dx *= table.getFriction();
                            } else if (dx < 0) {
                                dx *= table.getFriction();

                            }
                            if (dy > 0) {
                                dy *= table.getFriction();
                            } else if (dy < 0) {
                                dy *= table.getFriction();
                            }

                            if (p.checkIfPocketed(b)) {

                                if(b.c.equals("white")) {

                                    b.setPosX(b.getoPoxX());
                                    b.setPosY(b.getoPoxy());
                                    b.setVelY(0);
                                    b.setVelY(0);
                                    continue;
                                }

                                if(b.c.equals("blue")) {
                                    if(b.flag==1) {
                                        root.getChildren().remove(ball);
                                    }
                                    b.flag = 1;
                                    b.setPosX(b.getoPoxX());
                                    b.setPosY(b.getoPoxy());
                                    b.setVelY(0);
                                    b.setVelY(0);
                                    continue;
                                }

                                if(b.c.equals("red")) {
                                    root.getChildren().remove(ball);
                                    continue;
                                }
                            }

                            if (ch.checkWallCollisionX(b, table)) {
                                dx = -dx;
                            }
                            if (ch.checkWallCollisionY(b, table)) {
                                dy = -dy;
                            }

                            b.setPosX(b.getPosX() + dx);
                            b.setPosY(b.getPosY() + dy);

                            if (dx > -0.05 && dx < 0.05) {
                                dx = 0.0;
                            }
                            if (dy > -0.05 && dy < 0.05) {
                                dy = 0.0;
                            }

                            b.setVelX(dx);
                            b.setVelY(dy);

                            if (b.getTV() == 0) {
                                checkMovement = true;
                            } else {
                                checkMovement = false;
                            }
                            if (checkMovement) {
                                attachHandlers(root);
                            }
                        }

                        ch.handleCollisions(balls);


                    }
                }));


        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        primaryStage.show();


    }


    private void startDrag(Ball node) {
        if (dragActive)
            return;

        dragActive = true;
        currentLine = new Line();
        currentLine.setUserData(node);
        currentLine.setStartX(node.getCenterX());
        currentLine.setStartY(node.getCenterY());
        currentLine.endXProperty().bind(mouseX);
        currentLine.endYProperty().bind(mouseY);

        root.getChildren().add(currentLine);
    }



    private void stopDrag() {
        dragActive = false;
        cueBall.setPower(currentLine);
        cueBall.shoot(CueBall, currentLine);

        currentLine.endXProperty().unbind();
        currentLine.endYProperty().unbind();
        root.getChildren().remove(currentLine);

        currentLine = null;
    }

    private void attachHandlers(Group group) {
        group.setOnMouseMoved(e -> {
            mouseX.set(e.getSceneX());
            mouseY.set(e.getSceneY());
        });

        group.setOnMouseDragged(e -> {
            mouseX.set(e.getSceneX());
            mouseY.set(e.getSceneY());
        });

        group.setOnMousePressed(e -> {
            startDrag(CueBall);
        });

        group.setOnMouseReleased(e -> {
            stopDrag();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
