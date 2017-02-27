package gmap.drawer;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    private ArrayList<Polygon> mPolygons = new ArrayList<>();

    public Scene drawMap(ArrayList<AreaToDraw> areaToDraw) {

        Group root = new Group();

        Canvas canvas = new Canvas(1900, 900);

        drawStates(canvas.getGraphicsContext2D(), areaToDraw);

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        scene.setFill(Color.WHITE);

        return scene;
    }


    private void drawStates(GraphicsContext gc, ArrayList<AreaToDraw> areaToDraws) {
        double pointX[];
        double pointY[];
        for (AreaToDraw areaToDraw : areaToDraws) {  //cycle for states

            gc.setFill(Color.rgb(getColor(), getColor(), getColor()));
            gc.setStroke(Color.WHITE);

            for (ArrayList<Point> geoPoint :        //cycle for parts of states
                    areaToDraw.getArea()) {
                pointX = new double[geoPoint.size()];
                pointY = new double[geoPoint.size()];
                for (int q = 0; q < geoPoint.size(); q++) {     //cycle for dots
                    pointX[q] = geoPoint.get(q).getX();
                    pointY[q] = geoPoint.get(q).getY();
                }
                gc.fillPolygon(pointX, pointY, pointX.length);
            }

            drawLegend(gc);
        }

    }

    private void redrawPolygon(GraphicsContext gc, AreaToDraw areaToDraw) {

    }

    private int getColor() {
        return new Random().nextInt(255);
    }

    private void drawLegend(GraphicsContext gc) {
        Stop[] stops = new Stop[]{new Stop(0, Color.RED), new Stop(0.5, Color.WHITE), new Stop(1, Color.GREEN)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(lg1);
        gc.fillRect(50, 50, 1800, 15);

    }

}
