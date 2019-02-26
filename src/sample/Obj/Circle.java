package sample.Obj;

import javafx.scene.canvas.Canvas;

public class Circle extends Figure {
    @Override
    public void Draw(Canvas canvas, double x1, double y1, double x2, double y2) {
        double temp;
        if (y2<y1) {
            temp =y2;
            y2=y1;
            y1=temp;
        }
        if (x2<x1) {
            temp =x2;
            x2=x1;
            x1=temp;
        }
        canvas.getGraphicsContext2D().strokeOval(x1,y1,Math.abs(x1-x2),Math.abs(y2-y1));
        //canvas.getGraphicsContext2D().fillOval(x1,y1,Math.abs(x1-x2),Math.abs(y2-y1));

    }
}
