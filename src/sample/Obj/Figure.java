package sample.Obj;


import javafx.scene.canvas.Canvas;
import sample.Point;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public abstract   class Figure {

    //public abstract   void Draw(Canvas canvas, double x1, double y1, double x2, double y2);
    public  abstract LinkedList<Point> getPoints(Point fist, Point second);
    public  abstract Figure factoryMethod();

}
