package sample.Obj;

import javafx.scene.canvas.Canvas;
import sample.Temp;

public class Triangle extends Figure {
    @Override
    public void Draw(Canvas canvas, double x1, double y1, double x2, double y2) {
        Temp t1=swap(x1,y1,x2,y2);
        x1=t1.getX1();
        x2=t1.getX2();
        y1=t1.getY1();
        y2=t1.getY2();
        canvas.getGraphicsContext2D().strokeLine(x1, y1,x1, y2);
        canvas.getGraphicsContext2D().strokeLine(x1, y2,x2, y2);
        canvas.getGraphicsContext2D().strokeLine(x2, y2,x1, y1);
    }
}
