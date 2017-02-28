package gmap.drawer;

import gmap.JSONParser.AreaToDraw;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.awt.*;
import java.util.ArrayList;

public class Map {

    private Canvas mCanvas;

    public Scene getScene() {
        return mScene;
    }

    private Scene mScene;

    public Map() {
        Group root = new Group();

        mCanvas = new Canvas(1900, 900);
        root.getChildren().add(mCanvas);
//        root.getChildren().add();
        mScene = new Scene(root);
        mScene.setFill(Color.WHITE);
    }

    public Scene drawMap(ArrayList<AreaToDraw> areaToDraw) {
        for (AreaToDraw area :
                areaToDraw) {
            drawStates(mCanvas.getGraphicsContext2D(), area);
        }
        return mScene;
    }

    public void redrawPolygon(AreaToDraw areaToDraw) {
        drawStates(mCanvas.getGraphicsContext2D(), areaToDraw);
    }


    private void drawStates(GraphicsContext gc, AreaToDraw areaToDraw) {
        gc.setFill(areaToDraw.getColor());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        for (ArrayList<Point> geoPoint :        //cycle for parts of states
                areaToDraw.getArea()) {
            double pointX[] = new double[geoPoint.size()];
            double pointY[] = new double[geoPoint.size()];
            for (int q = 0; q < geoPoint.size(); q++) {     //cycle for dots
                pointX[q] = geoPoint.get(q).getX();
                pointY[q] = geoPoint.get(q).getY();
                if (q > 0)
                    gc.strokeLine(pointX[q], pointY[q], pointX[q - 1], pointY[q - 1]);
            }
            gc.fillPolygon(pointX, pointY, pointX.length);
        }

        drawLegend(gc);

    }

    private void drawLegend(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillText("-1", 40, 50);
        gc.fillText("0", 950, 50);
        gc.fillText("1", 1845, 50);

        Stop[] stops = new Stop[]{new Stop(0, Color.rgb(255, 0, 0)), new Stop(0.5, Color.WHITE), new Stop(1, Color.rgb(0, 255, 0))};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(lg1);
        gc.fillRect(50, 15, 1800, 15);
    }

    private int count = 0;
    public void showException(String text) {
        new Thread(() -> {
            GraphicsContext gc = mCanvas.getGraphicsContext2D();
            gc.setFill(Color.RED);
            gc.fillText(text.toUpperCase(), 20, 100);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gc.setFill(Color.WHITE);
            gc.fillRect(20, 80, 200, 30);


        }).start();

    }

}
