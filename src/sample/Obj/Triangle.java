package sample.Obj;

import javafx.scene.canvas.Canvas;

public class Triangle extends Figure {
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


        canvas.getGraphicsContext2D().strokeLine(x1, y1,x1, y2);
        canvas.getGraphicsContext2D().strokeLine(x1, y2,x2, y2);
        canvas.getGraphicsContext2D().strokeLine(x2, y2,x1, y1);
    }
}
